package com.bedrocklegends.woodenutilities.item.ring;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class PreciousTimeRing extends EffectRingItem{

    public PreciousTimeRing() {
        super(new EffectInstance(new EffectInstance(Effects.HASTE, 999999, 4)));
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("ring_tooltip.precious_time_ring"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
