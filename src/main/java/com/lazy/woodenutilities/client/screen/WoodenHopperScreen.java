package com.lazy.woodenutilities.client.screen;

import com.lazy.woodenutilities.inventory.containers.WoodenHopperContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class WoodenHopperScreen extends ContainerScreen<WoodenHopperContainer> {

    private static final ResourceLocation HOPPER_GUI_TEXTURE = new ResourceLocation("woodenutilities:textures/gui/container/wooden_hopper.png");

    public WoodenHopperScreen(WoodenHopperContainer container, PlayerInventory playerInv, ITextComponent containerName) {
        super(container, playerInv, containerName);
    }

    @Override
    public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) { //render
        this.func_230446_a_(matrixStack); //renderBackground
        super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(matrixStack, mouseX, mouseY); //renderHoveredToolTip
    }

    @Override
    protected void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY) { //drawGuiContainerForegroundLayer
        this.field_230712_o_.func_243248_b(matrixStack, this.field_230704_d_, (float)this.field_238742_p_, (float)this.field_238743_q_, 4210752);
        this.field_230712_o_.func_243248_b(matrixStack, this.playerInventory.getDisplayName(), (float)this.field_238744_r_, 39.0F, 4210752);
    }

    @Override
    protected void func_230450_a_(MatrixStack stack, float partialTicks, int mouseX, int mouseY) { //drawGuiContainerBackgroundLayer
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_230706_i_.getTextureManager().bindTexture(HOPPER_GUI_TEXTURE);
        int i = (this.field_230708_k_ - this.xSize) / 2;
        int j = (this.field_230709_l_ - this.ySize) / 2;
        this.func_238474_b_(stack, i, j, 0, 0, this.xSize, this.ySize);
    }
}
