package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.impl.baubles.IRing;
import com.bedrocklegends.woodenutilities.item.WoodenBucketItem;
import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WoodenItems {

    public static final ItemGroup WOODEN_UTILITIES_TAB = new ItemGroup("") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(WoodenItems.WOODEN_PLATE.get());
        }
    };

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WoodenUtilities.ID);
    public static final Map<String, RegistryObject<Item>> BUCKETS = new HashMap<>();

    public static final RegistryObject<Item> WOODEN_BUCKET = ITEMS.register("wooden_bucket", () -> new WoodenBucketItem(() -> Fluids.EMPTY, (new Item.Properties()).maxStackSize(16).group(WOODEN_UTILITIES_TAB)));
    public static final RegistryObject<Item> WOODEN_PLATE = ITEMS.register("wooden_plate", () -> new Item(new Item.Properties().group(WOODEN_UTILITIES_TAB)));

    //Rings
    public static final RegistryObject<Item> NIGHT_VISION = ITEMS.register("wooden_ring_night_vision", () -> new WoodenRingItem(new EffectInstance(Effects.NIGHT_VISION, 999999, 4)));
    public static final RegistryObject<Item> HASTE = ITEMS.register("wooden_ring_haste", () -> new WoodenRingItem(new EffectInstance(Effects.HASTE, 999999, 4)));
    public static final RegistryObject<Item> LUMBERJACK = ITEMS.register("lumberjack", WoodenRingItem::new);
    public static final RegistryObject<Item> MIDAS_TOUCH = ITEMS.register("midastouch", WoodenRingItem::new);
    public static final RegistryObject<Item> FLIGHT = ITEMS.register("wooden_ring_flight", () -> new WoodenRingItem(new IRing() {
        @Override
        public void onEquipped(PlayerEntity playerEntity, ItemStack stack) {
            if (!playerEntity.abilities.allowFlying) playerEntity.abilities.allowFlying = true;
        }

        @Override
        public void onUnequipped(PlayerEntity playerEntity, ItemStack stack) {
            if (playerEntity.abilities.allowFlying) playerEntity.abilities.allowFlying = false;
            if (playerEntity.abilities.isFlying) playerEntity.abilities.isFlying = false;
        }
    }));
    public static final RegistryObject<Item> MAGNET = ITEMS.register("magnet_ring", () -> new WoodenRingItem(new IRing() {
        @Override
        public void onWorn(PlayerEntity playerEntity, ItemStack stack) {
            List<ItemEntity> items = playerEntity.world.getEntitiesWithinAABB(ItemEntity.class, AxisAlignedBB.fromVector(playerEntity.getPositionVec()).grow(5));
            for (ItemEntity item : items) {
                item.setPosition(playerEntity.getPositionVec().x, playerEntity.getPositionVec().y, playerEntity.getPositionVec().z);
            }
        }
    }));
    public static final RegistryObject<Item> REPAIR = ITEMS.register("wooden_ring_repair", () -> new WoodenRingItem(new IRing() {
        @Override
        public void onWorn(PlayerEntity playerEntity, ItemStack stack) {
            for (ItemStack itemStack : playerEntity.inventory.armorInventory) {
                if (!itemStack.isEmpty() && itemStack.isDamaged()) {
                    if (playerEntity.experienceLevel >= 0 && playerEntity.experienceTotal > 0) {
                        itemStack.setDamage(itemStack.getDamage() - 1);
                        playerEntity.giveExperiencePoints(-1);
                    }
                }
            }
        }
    }));

    static {
        for (Fluid fluid : ForgeRegistries.FLUIDS) {
            if (fluid == Fluids.EMPTY || fluid.getRegistryName().getPath().startsWith("flowing")) {
                continue;
            }
            BUCKETS.put(Objects.requireNonNull(fluid.getRegistryName()).toString(), ITEMS.register("wooden_" + fluid.getRegistryName().getPath() + "_bucket", () -> new WoodenBucketItem(fluid::getFluid, (new Item.Properties()).maxStackSize(1).group(WOODEN_UTILITIES_TAB))));
        }
    }

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
