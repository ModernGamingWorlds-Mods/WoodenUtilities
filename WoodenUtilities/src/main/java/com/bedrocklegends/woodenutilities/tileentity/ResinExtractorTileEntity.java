package com.bedrocklegends.woodenutilities.tileentity;

import com.bedrocklegends.woodenutilities.api.APIUtils;
import com.bedrocklegends.woodenutilities.event.EventProvider;
import com.bedrocklegends.woodenutilities.resin.ResinProvider;
import com.bedrocklegends.woodenutilities.block.ResinExtractorBlock;
import com.bedrocklegends.woodenutilities.setup.WoodenFluids;
import com.bedrocklegends.woodenutilities.setup.WoodenTiles;
import com.bedrocklegends.woodenutilities.utility.NBTHelper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResinExtractorTileEntity extends TileEntity implements ITickableTileEntity {

    public static final String TAG_INTERNAL_RESIN = "StoredResin";
    private int internalResin = 0;
    public static final String TAG_IS_WORKING = "IsWorking";
    private boolean isWorking = false;
    public static final String TAG_CURRENT_TIME = "CurrentTime";
    private int currentTime = 0;
    public static final String TAG_BLOCKS_TO_DESTROY = "BlocksToDestroy";
    private List<BlockPos> blocksToExtractResin = Lists.newArrayList();

    private int maxStorage = 10000; //TODO: Max resin storage Config
    private final int finishTime = 20; //TODO: Work Time Config

    private Direction facing;

    public ResinExtractorTileEntity() {
        super(WoodenTiles.RESIN_EXTRACTOR.get());
    }



    @Override
    public CompoundNBT save(CompoundNBT compound) {
        CompoundNBT nbt = super.save(compound);
        nbt.putInt(TAG_INTERNAL_RESIN, this.internalResin);
        nbt.putInt(TAG_CURRENT_TIME, this.currentTime);
        nbt.putBoolean(TAG_IS_WORKING, this.isWorking);
        if (!this.blocksToExtractResin.isEmpty()) {
            nbt.put(TAG_BLOCKS_TO_DESTROY, NBTHelper.writeBlockPosList(this.blocksToExtractResin));
        }
        return nbt;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.internalResin = nbt.getInt(TAG_INTERNAL_RESIN);
        this.currentTime = nbt.getInt(TAG_CURRENT_TIME);
        this.isWorking = nbt.getBoolean(TAG_IS_WORKING);
        if (nbt.contains(TAG_BLOCKS_TO_DESTROY)) {
            this.blocksToExtractResin = NBTHelper.readBlockPosList(nbt.getCompound(TAG_BLOCKS_TO_DESTROY));
        }
    }

    @Override
    public void tick() {
        if (this.level != null && !this.level.isClientSide) {
            this.setFacing();
            this.increaseCurrentTime();
            if (this.currentTime >= this.finishTime) {
                this.extractResinFromProvider();
                this.transferLiquidResin();
                this.resetTimer();
            }
        }
    }

    public void startWorking() {
        Preconditions.checkNotNull(this.level);
        //TODO: Config or upgrade
        int xzMax = 5;
        int yMax = 20;
        this.blocksToExtractResin.addAll(BlockPos.betweenClosedStream(this.getBlockPos().offset(-xzMax, 0, -xzMax), this.getBlockPos().offset(xzMax, yMax, xzMax))
            .filter(blockPos -> APIUtils.getResinProviderFor(this.level.getBlockState(blockPos).getBlock()) != null)
            .map(BlockPos::mutable)
            .sorted(Comparator.comparing(BlockPos::getY))
            .collect(Collectors.toList()));

        if (!this.blocksToExtractResin.isEmpty()) {
            this.setIsWorking(true);
        }
    }

    private void extractResinFromProvider() {
        Preconditions.checkNotNull(this.level);
        if (this.isWorking && this.internalResin < this.maxStorage) {
            if (!this.blocksToExtractResin.isEmpty()) {
                BlockPos lastElement = this.blocksToExtractResin.get(this.blocksToExtractResin.size() - 1);
                ResinProvider resinProvider = APIUtils.getResinProviderFor(this.level.getBlockState(lastElement).getBlock());
                if (resinProvider != null) {
                    if (EventProvider.onExtractResinFromBlockPre(this.level, this.getBlockPos(), lastElement, resinProvider, this.internalResin)) {
                        this.blocksToExtractResin.remove(lastElement);
                        this.level.destroyBlock(lastElement, false);
                        this.increaseResin(resinProvider.getAmount());
                        this.setInternalResin(EventProvider.onExtractResinFromBlockPost(this.level, this.getBlockPos(), lastElement, resinProvider, this.internalResin));
                    }
                }
            } else {
                this.setIsWorking(false);
            }
        }
    }

    private void transferLiquidResin() {
        Preconditions.checkNotNull(this.level);
        BlockPos behindPos = this.getBlockPos().offset(this.facing.getOpposite().getNormal());
        boolean hasFluidStorageBehind = this.level.getBlockState(behindPos).hasTileEntity()
                && this.level.getBlockEntity(behindPos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).isPresent();

        if (!EventProvider.onExtractLiquidResin(level, getBlockPos())) return;
        if (this.internalResin <= 0) return;
        if (!hasFluidStorageBehind) return;
        IFluidHandler handler = this.level.getBlockEntity(behindPos).getTileEntity().getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
        handler.fill(new FluidStack(WoodenFluids.RESIN.get().getFluid(), this.internalResin), IFluidHandler.FluidAction.EXECUTE);
        this.increaseResin(-this.internalResin);
    }

    private void increaseCurrentTime() {
        this.currentTime++;
        this.setChanged();
    }

    private void resetTimer() {
        this.currentTime = 0;
        this.setChanged();
    }

    private void increaseResin(int amount) {
        this.internalResin = Math.min(this.internalResin + amount, this.maxStorage);
        this.setChanged();
    }

    private void setInternalResin(int amount) {
        this.internalResin = Math.min(amount, this.maxStorage);
        this.setChanged();
    }

    private void setIsWorking(boolean value) {
        this.isWorking = value;
        this.setChanged();
    }

    public BlockPos getFacingPos() {
        return this.getBlockPos().offset(this.facing.getNormal());
    }

    private void setFacing() {
        if (this.getBlockState().hasProperty(ResinExtractorBlock.FACING) && this.facing == null) {
            this.facing = this.getBlockState().getValue(ResinExtractorBlock.FACING);
        }
    }
}
