package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodenItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WoodenUtilities.ID);

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
