package com.lazy.woodenutilities.client.screen;

import com.lazy.woodenutilities.inventory.containers.WoodenSolarPanelContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WoodenSolarPanelScreen extends ContainerScreen<WoodenSolarPanelContainer> {
    private static final ResourceLocation SOLAR_PANEL_TEXTURE = new ResourceLocation("woodenutilities:textures/gui/container/wooden_solar_panel.png");

    public WoodenSolarPanelScreen(WoodenSolarPanelContainer container, PlayerInventory playerInv, ITextComponent containerName) {
        super(container, playerInv, containerName);
        this.passEvents = false;
        this.ySize = 133;
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        if(this.hoveredSlot == this.container.getSlot(0))
            this.renderTooltip("You can charge items that hold energy in here.", mouseX, mouseY);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
        this.font.drawString("Energy: " + this.container.getEnergy() + "/" + this.container.getMaxEnergy() + " (+" + this.container.getInput() + ")", 8.0F,23f, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) this.minecraft.getTextureManager().bindTexture(SOLAR_PANEL_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
    }
}
