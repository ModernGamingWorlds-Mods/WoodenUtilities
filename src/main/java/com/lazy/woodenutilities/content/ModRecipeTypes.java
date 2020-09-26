package com.lazy.woodenutilities.content;

import com.lazy.woodenutilities.WoodenUtilities;
import com.lazy.woodenutilities.util.WoodCutterRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class ModRecipeTypes {

    public static final IRecipeType<WoodCutterRecipe> WOOD_CUTTER = addRecipeType(new ResourceLocation(WoodenUtilities.MOD_ID, "woodcutter"));

    private static <T extends IRecipe<?>> IRecipeType<T> addRecipeType(final ResourceLocation resource) {
        return Registry.register(Registry.RECIPE_TYPE, resource, new IRecipeType<T>() {
            @Override
            public String toString() {
                return resource.toString();
            }
        });
    }

}
