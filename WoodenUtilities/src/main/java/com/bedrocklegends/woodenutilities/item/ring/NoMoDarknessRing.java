package com.bedrocklegends.woodenutilities.item.ring;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class NoMoDarknessRing extends EffectRingItem {

    public NoMoDarknessRing() {
        super(new EffectInstance(Effects.NIGHT_VISION, 999999, 4));
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("ring_tooltip.no_mo_darkness_ring"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
