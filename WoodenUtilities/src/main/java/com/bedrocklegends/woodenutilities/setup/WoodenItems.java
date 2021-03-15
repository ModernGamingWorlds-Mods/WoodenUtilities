package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.item.WoodenBucketItem;
import com.bedrocklegends.woodenutilities.utility.WoodenConstants;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
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
            .register(WoodenConstants.Items.WOODEN_BUCKET, () -> new WoodenBucketItem(() -> Fluids.EMPTY, (new Item.Properties())
                    .stacksTo(16).tab(WoodenItemGroup.INSTANCE)));
    public static final RegistryObject<Item> WOODEN_PLATE = ITEMS
            .register(WoodenConstants.Items.WOODEN_PLATE, () -> new Item(new Item.Properties().tab(WoodenItemGroup.INSTANCE)));

    //Universal Resin Bucket
    public static final RegistryObject<Item> RESIN_BUCKET = ITEMS.register(WoodenConstants.Items.RESIN_BUCKET,
            () -> new BucketItem(() -> WoodenFluids.RESIN.get(), (new Item.Properties()).stacksTo(16).tab(WoodenItemGroup.INSTANCE)));

    //Rings
    public static final RegistryObject<Item> WOODEN_TANK = ITEMS
            .register(WoodenConstants.Blocks.WOODEN_TANK, () -> new BlockItem(WoodenBlocks.WOODEN_TANK.get(), new Item.Properties()
                    .tab(WoodenItemGroup.INSTANCE)));

    public static final RegistryObject<Item> RESIN_EXTRACTOR = ITEMS
            .register(WoodenConstants.Blocks.RESIN_EXTRACTOR, () -> new BlockItem(WoodenBlocks.RESIN_EXTRACTOR.get(), new Item.Properties()
                    .tab(WoodenItemGroup.INSTANCE)));

    static {
        for (Fluid fluid : ForgeRegistries.FLUIDS) {
            if (fluid == Fluids.EMPTY || fluid.getRegistryName().getPath().startsWith("flowing")) {
                continue;
            }
            BUCKETS.put(Objects.requireNonNull(fluid.getRegistryName()).toString(), ITEMS
                    .register("wooden_" + fluid.getRegistryName()
                            .getPath() + "_bucket", () -> new WoodenBucketItem(fluid::getFluid, (new Item.Properties())
                            .stacksTo(1).tab(WoodenItemGroup.INSTANCE))));
        }
    }

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
