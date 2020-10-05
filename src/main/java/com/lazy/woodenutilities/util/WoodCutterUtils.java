package com.lazy.woodenutilities.util;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.stream.Collectors;

public class WoodCutterUtils {

    public static Map<String, List<Item>> getWoodVariants() {
        Map<String, List<Item>> woodVariants = new HashMap<>();
        ForgeRegistries.ITEMS.forEach((item) -> {
            if (item.getRegistryName() != null) {
                if (item.getRegistryName().getPath().contains("planks")) {
                    String plankType = item.getRegistryName().getPath().replace("_planks", "");
                    List<Item> variants = new ArrayList<>();
                    ForgeRegistries.ITEMS.forEach((item1) -> {
                        if (item1.getRegistryName() != null) {
                            //TODO: Please slap me and tell me how to do this in a better way
                            String registryName = item1.getRegistryName().toString();
                            if (registryName.contains(plankType)) {
                                if (registryName.contains(":" + plankType + "_stairs")
                                        || registryName.contains(":" + plankType + "_slab")
                                        || registryName.contains(":" + plankType + "_door")
                                        || registryName.contains(":" + plankType + "_sign")
                                        || registryName.contains(":" + plankType + "_pressure_plate")
                                        || registryName.contains(":" + plankType + "_fence")
                                        || registryName.contains(":" + plankType + "_fence_gate")
                                        || registryName.contains(":" + plankType + "_trapdoor")
                                        || registryName.contains(":" + plankType + "_button")) {
                                    variants.add(item1);
                                }
                            }
                        }
                    });
                    woodVariants.put(plankType, variants);
                }
            }
        });
        return woodVariants;
    }

    public static Collection<WoodCutterRecipe> createRecipesForJEI() {
        Collection<WoodCutterRecipe> woodCutterRecipes = Minecraft.getInstance().world.getRecipeManager().getRecipes().stream().filter(WoodCutterRecipe.class::isInstance).map(WoodCutterRecipe.class::cast).collect(Collectors.toList());

        getWoodVariants().forEach((key, values) -> {
            Item item = ForgeRegistries.ITEMS.getValues().stream().filter(i -> i.getRegistryName().toString().contains(":" + key + "_planks")).collect(Collectors.toList()).get(0);
            values.forEach((i) -> {
                ItemStack result;
                if (i.getRegistryName().getPath().contains("slab")) {
                    result = new ItemStack(i, 2);
                } else {
                    result = new ItemStack(i);
                }
                WoodCutterRecipe recipe = new WoodCutterRecipe(Ingredient.fromStacks(new ItemStack(item)), result);
                woodCutterRecipes.add(recipe);
            });
        });

        return woodCutterRecipes;
    }
}
