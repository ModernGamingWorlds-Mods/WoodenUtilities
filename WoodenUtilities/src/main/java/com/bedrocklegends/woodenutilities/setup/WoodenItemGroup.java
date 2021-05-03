package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.config.holder.CommonConfigHolder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class WoodenItemGroup extends ItemGroup {

    public static final ItemGroup INSTANCE = new WoodenItemGroup("wooden_utilities");

    public WoodenItemGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(WoodenItems.WOODEN_PLATE.get());
    }

}