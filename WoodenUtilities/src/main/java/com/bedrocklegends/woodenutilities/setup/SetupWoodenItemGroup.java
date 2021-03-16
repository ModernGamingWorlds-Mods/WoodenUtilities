package com.bedrocklegends.woodenutilities.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class SetupWoodenItemGroup extends ItemGroup {

    public static final ItemGroup INSTANCE = new SetupWoodenItemGroup("wooden_utilities");

    public SetupWoodenItemGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(SetupWoodenItems.WOODEN_PLATE.get());
    }
}