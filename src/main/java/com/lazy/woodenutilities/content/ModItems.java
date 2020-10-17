package com.lazy.woodenutilities.content;

import com.lazy.woodenutilities.WoodenUtilities;
import com.lazy.woodenutilities.item.BasicWoodItem;
import com.lazy.woodenutilities.item.WoodenBackpack;
import com.lazy.woodenutilities.item.WoodenBucketItem;
import com.lazy.woodenutilities.item.WoodenShearsItem;
import com.lazy.woodenutilities.util.WoodArmorMaterial;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WoodenUtilities.MOD_ID);

    public static RegistryObject<BasicWoodItem> WOODEN_PLATE;
    public static RegistryObject<WoodenBackpack> WOODEN_BACKPACK;

    public static void init(IEventBus bus){
        WOODEN_PLATE = ITEMS.register("wooden_plate", BasicWoodItem::new);
        WOODEN_BACKPACK = ITEMS.register("wooden_backpack", WoodenBackpack::new);

        ITEMS.register("wooden_shears", WoodenShearsItem::new);
        ITEMS.register("wooden_helmet", ()->new ArmorItem(WoodArmorMaterial.getMaterial(), EquipmentSlotType.HEAD, new Item.Properties().group(WoodenUtilities.WOODEN_UTILITIES)));
        ITEMS.register("wooden_chestplate", ()->new ArmorItem(WoodArmorMaterial.getMaterial(), EquipmentSlotType.CHEST, new Item.Properties().group(WoodenUtilities.WOODEN_UTILITIES)));
        ITEMS.register("wooden_leggings", ()->new ArmorItem(WoodArmorMaterial.getMaterial(), EquipmentSlotType.LEGS, new Item.Properties().group(WoodenUtilities.WOODEN_UTILITIES)));
        ITEMS.register("wooden_boot", ()->new ArmorItem(WoodArmorMaterial.getMaterial(), EquipmentSlotType.FEET, new Item.Properties().group(WoodenUtilities.WOODEN_UTILITIES)));
        ITEMS.register("wooden_horse_armor", ()-> new HorseArmorItem(7, new ResourceLocation(WoodenUtilities.MOD_ID, "textures/entity/horse/armor/horse_armor_wood.png"), new Item.Properties().group(WoodenUtilities.WOODEN_UTILITIES)));

        ITEMS.register(bus);
    }


    public static void addBlockItem(Block block, String registryName) {
        Item.Properties properties = new Item.Properties().group(WoodenUtilities.WOODEN_UTILITIES);
        ModItems.ITEMS.register(registryName, () -> new BlockItem(block, properties));
    }
}
