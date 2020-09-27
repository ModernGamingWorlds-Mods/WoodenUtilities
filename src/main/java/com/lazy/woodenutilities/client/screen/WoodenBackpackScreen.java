package com.lazy.woodenutilities.client.screen;

import com.lazy.woodenutilities.WoodenUtilities;
import com.lazy.woodenutilities.inventory.containers.WoodenBackpackContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WoodenBackpackScreen extends ContainerScreen<WoodenBackpackContainer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(WoodenUtilities.MOD_ID, "textures/gui/container/wooden_backpack.png");

    public WoodenBackpackScreen(WoodenBackpackContainer containerIn, PlayerInventory playerInv, ITextComponent titleIn) {
        super(containerIn, playerInv, titleIn);
    }

    @Override
    public void func_230430_a_(MatrixStack stack, int mouseX, int mouseY, float partialTicks) { //render
        super.func_230430_a_(stack, mouseX, mouseY, partialTicks); //render
        this.func_230459_a_(stack, mouseX, mouseY); //renderHoveredTooltips
    }

    @Override
    protected void func_230450_a_(MatrixStack stack, float partialTicks, int mouseX, int mouseY) { //drawGuiContainerBackgroundLayer
        this.func_230446_a_(stack); //renderBackground
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_230706_i_.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.func_238474_b_(stack, i, j, 0, 0, this.xSize, this.ySize); //blit
    }
}