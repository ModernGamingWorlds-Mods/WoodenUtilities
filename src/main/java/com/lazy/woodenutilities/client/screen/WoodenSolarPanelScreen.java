package com.lazy.woodenutilities.client.screen;

import com.lazy.woodenutilities.inventory.containers.WoodenSolarPanelContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.Collections;

public class WoodenSolarPanelScreen extends ContainerScreen<WoodenSolarPanelContainer> {

    private static final ResourceLocation SOLAR_PANEL_TEXTURE = new ResourceLocation("woodenutilities:textures/gui/container/wooden_solar_panel.png");

    public WoodenSolarPanelScreen(WoodenSolarPanelContainer container, PlayerInventory playerInv, ITextComponent containerName) {
        super(container, playerInv, containerName);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
        if (this.hoveredSlot == this.container.getSlot(0) && this.container.getSlot(0).getStack().getItem() == Items.AIR) {
            this.func_243308_b(matrixStack, Collections.singletonList(new StringTextComponent("You can charge items that hold energy in here.")), mouseX, mouseY);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.font.func_243248_b(matrixStack, this.title, (float) this.titleX, (float) this.titleY, 4210752);
        this.font.func_243248_b(matrixStack, this.playerInventory.getDisplayName(), (float) this.playerInventoryTitleX, 39.0F, 4210752);
        this.font.func_243248_b(matrixStack, new StringTextComponent("Energy: " + this.container.getEnergy() + "/" + this.container.getMaxEnergy() + " (+" + this.container.getInput() + ")"), 8.0F, 23F, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) { //drawGuiContainerBackgroundLayer
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(SOLAR_PANEL_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(stack, i, j, 0, 0, this.xSize, this.ySize);
    }
}