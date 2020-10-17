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
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(stack);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(stack, i, j, 0, 0, this.xSize, this.ySize);
    }
}