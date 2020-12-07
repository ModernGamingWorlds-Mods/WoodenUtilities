package com.bedrocklegends.woodenutilities.item.ring;

import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class MidasTouchRing extends WoodenRingItem {

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("ring_tooltip.midas_touch_ring"));
    }
}
