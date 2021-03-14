package com.bedrocklegends.woodenutilities.tile;

import com.bedrocklegends.woodenutilities.api.APIUtils;
import com.bedrocklegends.woodenutilities.api.event.EventManager;
import com.bedrocklegends.woodenutilities.api.resin.ResinProvider;
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

public class ResinExtractorTile extends TileEntity implements ITickableTileEntity {

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

    public ResinExtractorTile() {
        super(WoodenTiles.RESIN_EXTRACTOR.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT nbt = super.write(compound);
        nbt.putInt(TAG_INTERNAL_RESIN, this.internalResin);
        nbt.putInt(TAG_CURRENT_TIME, this.currentTime);
        nbt.putBoolean(TAG_IS_WORKING, this.isWorking);
        if (!this.blocksToExtractResin.isEmpty()) {
            nbt.put(TAG_BLOCKS_TO_DESTROY, NBTHelper.writeBlockPosList(this.blocksToExtractResin));
        }
        return nbt;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.internalResin = nbt.getInt(TAG_INTERNAL_RESIN);
        this.currentTime = nbt.getInt(TAG_CURRENT_TIME);
        this.isWorking = nbt.getBoolean(TAG_IS_WORKING);
        if (nbt.contains(TAG_BLOCKS_TO_DESTROY)) {
            this.blocksToExtractResin = NBTHelper.readBlockPosList(nbt.getCompound(TAG_BLOCKS_TO_DESTROY));
        }
    }

    @Override
    public void tick() {
        if (this.world != null && !this.world.isRemote) {
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
        Preconditions.checkNotNull(this.world);
        //TODO: Config or upgrade
        int xzMax = 5;
        int yMax = 20;
        this.blocksToExtractResin.addAll(BlockPos.getAllInBox(this.getPos().add(-xzMax, 0, -xzMax), this.getPos().add(xzMax, yMax, xzMax))
                .filter(blockPos -> APIUtils.getResinProviderFor(this.world.getBlockState(blockPos).getBlock()) != null)
                .map(BlockPos::toMutable)
                .sorted(Comparator.comparing(BlockPos::getY))
                .collect(Collectors.toList()));

        if (!this.blocksToExtractResin.isEmpty()) {
            this.setIsWorking(true);
        }
    }

    private void extractResinFromProvider() {
        Preconditions.checkNotNull(this.world);
        if (this.isWorking && this.internalResin < this.maxStorage) {
            if (!this.blocksToExtractResin.isEmpty()) {
                BlockPos lastElement = this.blocksToExtractResin.get(this.blocksToExtractResin.size() - 1);
                ResinProvider resinProvider = APIUtils.getResinProviderFor(this.world.getBlockState(lastElement).getBlock());
                if (resinProvider != null) {
                    EventManager.onExtractResinFromBlockPre(this.world, this.pos, lastElement, resinProvider, this.internalResin);
                    this.blocksToExtractResin.remove(lastElement);
                    this.world.destroyBlock(lastElement, false);
                    this.increaseResin(resinProvider.getAmount());
                    this.setInternalResin(EventManager.onExtractResinFromBlockPost(this.world, this.pos, lastElement, resinProvider, this.internalResin));
                }
            } else {
                this.setIsWorking(false);
            }
        }
    }

    private void transferLiquidResin() {
        Preconditions.checkNotNull(this.world);
        BlockPos behindPos = this.getPos().offset(this.facing.getOpposite());
        boolean hasFluidStorageBehind = this.world.getBlockState(behindPos).hasTileEntity()
                && this.world.getTileEntity(behindPos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).isPresent();

        if (!EventManager.onExtractLiquidResin(world, pos)) return;
        if (this.internalResin <= 0) return;
        if (!hasFluidStorageBehind) return;
        IFluidHandler handler = this.world.getTileEntity(behindPos).getTileEntity().getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
        handler.fill(new FluidStack(WoodenFluids.RESIN.get().getFluid(), this.internalResin), IFluidHandler.FluidAction.EXECUTE);
        this.increaseResin(-this.internalResin);
    }

    private void increaseCurrentTime() {
        this.currentTime++;
        this.markDirty();
    }

    private void resetTimer() {
        this.currentTime = 0;
        this.markDirty();
    }

    private void increaseResin(int amount) {
        this.internalResin = Math.min(this.internalResin + amount, this.maxStorage);
        this.markDirty();
    }

    private void setInternalResin(int amount) {
        this.internalResin = Math.min(amount, this.maxStorage);
        this.markDirty();
    }

    private void setIsWorking(boolean value) {
        this.isWorking = value;
        this.markDirty();
    }

    public BlockPos getFacingPos() {
        return this.getPos().offset(this.facing);
    }

    private void setFacing() {
        if (this.getBlockState().hasProperty(ResinExtractorBlock.FACING) && this.facing == null) {
            this.facing = this.getBlockState().get(ResinExtractorBlock.FACING);
        }
    }
}
