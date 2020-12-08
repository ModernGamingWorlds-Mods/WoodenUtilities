package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.block.WoodenTankBlock;
import com.bedrocklegends.woodenutilities.item.WoodenBucketItem;
import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import com.bedrocklegends.woodenutilities.item.ring.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WoodenItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WoodenUtilities.ID);
    public static final Map<String, RegistryObject<Item>> BUCKETS = new HashMap<>();

    public static final RegistryObject<Item> WOODEN_BUCKET = ITEMS.register("wooden_bucket", () -> new WoodenBucketItem(() -> Fluids.EMPTY, (new Item.Properties()).maxStackSize(16).group(WoodenItemGroup.INSTANCE)));
    public static final RegistryObject<Item> WOODEN_PLATE = ITEMS.register("wooden_plate", () -> new Item(new Item.Properties().group(WoodenItemGroup.INSTANCE)));

    //Rings
    public static final RegistryObject<Item> NO_MO_DARKNESS_RING = ITEMS.register("no_mo_darkness", NoMoDarknessRing::new);
    public static final RegistryObject<Item> PRECIOUS_TIME_RING = ITEMS.register("precious_time_ring", PreciousTimeRing::new);
    public static final RegistryObject<Item> THE_LUMBERJACK_RING = ITEMS.register("the_lumberjack", TheLumberjackRing::new);
    public static final RegistryObject<Item> MIDAS_TOUCH_RING = ITEMS.register("midas_touch", MidasTouchRing::new);
    public static final RegistryObject<Item> FLIGHT_RING = ITEMS.register("flight_ring", FlightRingItem::new);
    public static final RegistryObject<Item> MAGNET_RING = ITEMS.register("magnet_ring", MagnetRingItem::new);
    public static final RegistryObject<Item> FORGED_RING = ITEMS.register("forged_ring", ForgedRingItem::new);

    public static final RegistryObject<Item> WOODEN_TANK = ITEMS.register("wooden_tank", () -> new BlockItem(WoodenBlocks.WOODEN_TANK.get(), new Item.Properties().group(WoodenItemGroup.INSTANCE)));

    static {
        for (Fluid fluid : ForgeRegistries.FLUIDS) {
            if (fluid == Fluids.EMPTY || fluid.getRegistryName().getPath().startsWith("flowing")) {
                continue;
            }
            BUCKETS.put(Objects.requireNonNull(fluid.getRegistryName()).toString(), ITEMS.register("wooden_" + fluid.getRegistryName().getPath() + "_bucket", () -> new WoodenBucketItem(fluid::getFluid, (new Item.Properties()).maxStackSize(1).group(WoodenItemGroup.INSTANCE))));
        }
    }

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
