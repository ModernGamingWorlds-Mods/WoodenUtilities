package com.lazy.woodenutilities.tiles;

import com.lazy.woodenutilities.content.ModTiles;
import com.lazy.woodenutilities.inventory.containers.WoodenFurnaceContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WoodenFurnaceTileEntity extends AbstractFurnaceTileEntity {

    public WoodenFurnaceTileEntity() {
        super(ModTiles.WOODEN_FURNACE_TILE.get(), IRecipeType.SMELTING);
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.wooden_furnace");
    }

    protected Container createMenu(int id, PlayerInventory player) {
        return new WoodenFurnaceContainer(id, player, this, this.furnaceData);
    }
}
