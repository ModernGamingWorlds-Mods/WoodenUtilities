package com.bedrocklegends.woodenutilities.setup;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.bedrocklegends.woodenutilities.item.WoodenBucketItem;
import com.bedrocklegends.woodenutilities.util.WoodenConstants;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SetupWoodenItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister
            .create(ForgeRegistries.ITEMS, WoodenConstants.MODID);
    public static final Map<String, RegistryObject<Item>> BUCKETS = new HashMap<>();

    public static final RegistryObject<Item> WOODEN_BUCKET = ITEMS
            .register(WoodenConstants.Items.WOODEN_BUCKET, () -> new WoodenBucketItem(() -> Fluids.EMPTY, (new Item.Properties())
                    .stacksTo(16).tab(SetupWoodenItemGroup.INSTANCE)));
    public static final RegistryObject<Item> WOODEN_PLATE = ITEMS
            .register(WoodenConstants.Items.WOODEN_PLATE, () -> new Item(new Item.Properties().tab(SetupWoodenItemGroup.INSTANCE)));

    //Universal Resin Bucket
    public static final RegistryObject<Item> RESIN_BUCKET = ITEMS.register(WoodenConstants.Items.RESIN_BUCKET,
            () -> new BucketItem(() -> SetupWoodenFluids.RESIN.get(), (new Item.Properties()).stacksTo(16).tab(SetupWoodenItemGroup.INSTANCE)));

    //Rings
    public static final RegistryObject<Item> WOODEN_TANK = ITEMS
            .register(WoodenConstants.Blocks.WOODEN_TANK, () -> new BlockItem(SetupWoodenBlocks.WOODEN_TANK.get(), new Item.Properties()
                    .tab(SetupWoodenItemGroup.INSTANCE)));

    public static final RegistryObject<Item> RESIN_EXTRACTOR = ITEMS
            .register(WoodenConstants.Blocks.RESIN_EXTRACTOR, () -> new BlockItem(SetupWoodenBlocks.RESIN_EXTRACTOR.get(), new Item.Properties()
                    .tab(SetupWoodenItemGroup.INSTANCE)));

    static {
        for (Fluid fluid : ForgeRegistries.FLUIDS) {
            if (fluid == Fluids.EMPTY || fluid.getRegistryName().getPath().startsWith("flowing")) {
                continue;
            }
            BUCKETS.put(Objects.requireNonNull(fluid.getRegistryName()).toString(), ITEMS
                    .register("wooden_" + fluid.getRegistryName()
                            .getPath() + "_bucket", () -> new WoodenBucketItem(fluid::getFluid, (new Item.Properties())
                            .stacksTo(1).tab(SetupWoodenItemGroup.INSTANCE))));
        }
    }

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
