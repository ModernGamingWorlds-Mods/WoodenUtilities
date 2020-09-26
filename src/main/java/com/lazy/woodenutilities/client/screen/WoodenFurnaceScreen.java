package com.lazy.woodenutilities.client.screen;

import com.lazy.woodenutilities.inventory.containers.WoodenFurnaceContainer;
import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class WoodenFurnaceScreen extends AbstractFurnaceScreen<WoodenFurnaceContainer> {

    private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation("textures/gui/container/furnace.png");

    public WoodenFurnaceScreen(WoodenFurnaceContainer container, PlayerInventory playerInventory, ITextComponent displayName) {
        super(container, new FurnaceRecipeGui(), playerInventory, displayName, FURNACE_GUI_TEXTURES);
    }
}
