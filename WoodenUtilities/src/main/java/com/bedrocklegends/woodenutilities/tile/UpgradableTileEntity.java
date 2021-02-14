package com.bedrocklegends.woodenutilities.tile;

import com.bedrocklegends.woodenutilities.utility.IUpgradable;
import com.bedrocklegends.woodenutilities.utility.NBTHelper;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;
import java.util.List;

public class UpgradableTileEntity extends TileEntity implements IUpgradable {

    public static final String TAG_NUM_UPGRADES = "NumOfUpgrades";
    private int numOfUpgrades;
    public static final String TAG_UPGRADES = "Upgrades";
    private List<ItemStack> upgrades = Lists.newArrayList();

    public UpgradableTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    @Nonnull
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        CompoundNBT nbt = super.write(compound);
        nbt.putInt(TAG_NUM_UPGRADES, this.numOfUpgrades);
        if (!this.upgrades.isEmpty()) {
            nbt.put(TAG_UPGRADES, NBTHelper.writeItemStackList(this.upgrades));
        }
        return nbt;
    }

    @Override
    public void read(@Nonnull BlockState state, @Nonnull CompoundNBT nbt) {
        super.read(state, nbt);
        this.numOfUpgrades = nbt.getInt(TAG_NUM_UPGRADES);
        if (nbt.contains(TAG_UPGRADES)) {
            this.upgrades = NBTHelper.readItemStackList(nbt.getCompound(TAG_UPGRADES));
        }
    }

    @Override
    public TileEntity getUpgradableTile() {
        return this;
    }

    @Override
    public int getNumOfUpgrades() {
        return this.numOfUpgrades;
    }

    @Override
    public void setNumOfUpgrades(int amount) {
        this.numOfUpgrades = amount;
    }

    @Override
    public List<ItemStack> getUpgrades() {
        return this.upgrades;
    }
}
