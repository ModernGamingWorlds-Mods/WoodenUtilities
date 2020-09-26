package com.lazy.woodenutilities.content;

import com.lazy.woodenutilities.WoodenUtilities;
import com.lazy.woodenutilities.util.WoodCutterRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = WoodenUtilities.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRecipeSerializers {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, WoodenUtilities.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<WoodCutterRecipe>> WOODCUTTING = RECIPE_SERIALIZERS.register("woodcutting",
            () -> new WoodCutterRecipe.Serializer<>(WoodCutterRecipe::new));
}