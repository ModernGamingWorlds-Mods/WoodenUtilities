package com.lazy.woodenutilities.util;

import com.google.gson.JsonObject;
import com.lazy.woodenutilities.content.ModBlocks;
import com.lazy.woodenutilities.content.ModRecipeSerializers;
import com.lazy.woodenutilities.content.ModRecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class WoodCutterRecipe extends SingleItemRecipe {

    public WoodCutterRecipe(ResourceLocation recipeLoaction, String group, Ingredient ingredient, ItemStack result) {
        super(ModRecipeTypes.WOOD_CUTTER, ModRecipeSerializers.WOODCUTTING.get(), recipeLoaction, group, ingredient, result);
    }

    public boolean matches(IInventory inv, World worldIn) {
        return this.ingredient.test(inv.getStackInSlot(0));
    }

    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.WOOD_CUTTER.get());
    }

    public static class Serializer<T extends WoodCutterRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
        private final WoodCutterRecipe.Serializer.IRecipeFactory<T> factory;

        public Serializer(WoodCutterRecipe.Serializer.IRecipeFactory<T> factory) {
            this.factory = factory;
        }

        @Override
        public T read(ResourceLocation recipeId, JsonObject json) {
            String group = JSONUtils.getString(json, "group", "");
            Ingredient ingredient;
            if (JSONUtils.isJsonArray(json, "ingredient")) {
                ingredient = Ingredient.deserialize(JSONUtils.getJsonArray(json, "ingredient"));
            } else {
                ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "ingredient"));
            }
            String result = JSONUtils.getString(json, "result");
            int count = JSONUtils.getInt(json, "count");
            ItemStack stack = new ItemStack(Registry.ITEM.getOrDefault(new ResourceLocation(result)), count);
            return this.factory.create(recipeId, group, ingredient, stack);
        }

        @Override
        public T read(ResourceLocation recipeId, PacketBuffer buffer) {
            String group = buffer.readString(32767);
            Ingredient ingredient = Ingredient.read(buffer);
            ItemStack stack = buffer.readItemStack();
            return this.factory.create(recipeId, group, ingredient, stack);
        }

        @Override
        public void write(PacketBuffer buffer, T recipe) {
            buffer.writeString(recipe.group);
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
        }

        public interface IRecipeFactory<T extends WoodCutterRecipe> {
            T create(ResourceLocation id, String group, Ingredient ingredient, ItemStack stack);
        }
    }
}
