package com.bedrocklegends.woodenutilities.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class WoodenItemGroup extends ItemGroup {

    public static final ItemGroup INSTANCE = new WoodenItemGroup("wooden_utilities");

    public WoodenItemGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(WoodenItems.WOODEN_PLATE.get());
    }
}