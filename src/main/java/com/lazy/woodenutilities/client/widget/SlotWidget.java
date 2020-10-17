package com.lazy.woodenutilities.client.widget;

import com.lazy.woodenutilities.WoodenUtilities;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class SlotWidget extends Widget {

    private static final ResourceLocation BUTTON_STYLES = new ResourceLocation(WoodenUtilities.MOD_ID, "textures/gui/button_styles.png");

    public SlotWidget(int xIn, int yIn) {
        super(xIn, yIn, 18, 18, new StringTextComponent(""));
    }

    @Override
    protected boolean isValidClickButton(int p_isValidClickButton_1_) {
        return false;
    }

    @Override
    public void renderButton(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();

        mc.getTextureManager().bindTexture(BUTTON_STYLES);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.blit(stack, this.x, this.y, 0, 0, this.width, this.height);
    }
}
