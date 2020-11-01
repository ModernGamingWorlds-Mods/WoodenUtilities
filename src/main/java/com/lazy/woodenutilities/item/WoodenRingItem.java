package com.lazy.woodenutilities.item;

import com.lazy.baubles.api.BaubleType;
import com.lazy.baubles.api.IBauble;
import com.lazy.woodenutilities.WoodenUtilities;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class WoodenRingItem extends Item implements IBauble {

    private final List<String> tooltips;

    public WoodenRingItem(List<String> tooltips) {
        super(new Properties().group(WoodenUtilities.WOODEN_UTILITIES).maxStackSize(1));
        this.tooltips = tooltips;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        for(String s : tooltips){
            tooltip.add(new StringTextComponent(s));
        }
    }

    @Override
    public BaubleType getBaubleType() {
        return BaubleType.RING;
    }
}
