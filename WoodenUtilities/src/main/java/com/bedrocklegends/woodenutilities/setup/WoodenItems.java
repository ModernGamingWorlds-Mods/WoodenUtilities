package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.item.WoodenBucketItem;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WoodenItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister
        .create(ForgeRegistries.ITEMS, WoodenUtilities.ID);
    public static final Map<String, RegistryObject<Item>> BUCKETS = new HashMap<>();

    public static final RegistryObject<Item> WOODEN_BUCKET = ITEMS
        .register("wooden_bucket", () -> new WoodenBucketItem(() -> Fluids.EMPTY, (new Item.Properties())
            .maxStackSize(16).group(WoodenItemGroup.INSTANCE)));
    public static final RegistryObject<Item> WOODEN_PLATE = ITEMS
        .register("wooden_plate", () -> new Item(new Item.Properties().group(WoodenItemGroup.INSTANCE)));

    //Rings
    public static final RegistryObject<Item> WOODEN_TANK = ITEMS
        .register("wooden_tank", () -> new BlockItem(WoodenBlocks.WOODEN_TANK.get(), new Item.Properties()
            .group(WoodenItemGroup.INSTANCE)));

    static {
        for (Fluid fluid : ForgeRegistries.FLUIDS) {
            if (fluid == Fluids.EMPTY || fluid.getRegistryName().getPath().startsWith("flowing")) {
                continue;
            }
            BUCKETS.put(Objects.requireNonNull(fluid.getRegistryName()).toString(), ITEMS
                .register("wooden_" + fluid.getRegistryName()
                    .getPath() + "_bucket", () -> new WoodenBucketItem(fluid::getFluid, (new Item.Properties())
                    .maxStackSize(1).group(WoodenItemGroup.INSTANCE))));
        }
    }

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
