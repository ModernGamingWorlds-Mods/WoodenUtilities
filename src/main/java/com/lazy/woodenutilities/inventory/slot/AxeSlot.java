package com.lazy.woodenutilities.inventory.slot;

import com.lazy.woodenutilities.WoodenUtilities;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class AxeSlot extends Slot {

    private boolean isEnabled = true;

    public AxeSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ToolItem && stack.getToolTypes().contains(ToolType.AXE);
    }

    public ToolItem getToolItem() {
        return (ToolItem) this.getStack().getItem();
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        return super.onTake(thePlayer, stack);
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Pair<ResourceLocation, ResourceLocation> func_225517_c_() { //getBackground
        return Pair.of(PlayerContainer.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(WoodenUtilities.MOD_ID, "item/axe_slot_empty"));
    }
}
