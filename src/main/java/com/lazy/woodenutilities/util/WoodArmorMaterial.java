package com.lazy.woodenutilities.util;

import com.lazy.woodenutilities.content.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class WoodArmorMaterial {

    public static IArmorMaterial getMaterial(){
        return new IArmorMaterial() {
            @Override
            public int getDurability(EquipmentSlotType slotIn) {
                int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
                return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * 7;
            }

            @Override
            public int getDamageReductionAmount(EquipmentSlotType slotIn) {
                int[] damageReductionAmountArray = new int[]{1, 3, 5, 2};
                return damageReductionAmountArray[slotIn.getIndex()];
            }

            @Override
            public int getEnchantability() {
                return 25;
            }

            @Override
            public SoundEvent getSoundEvent() {
                return SoundEvents.BLOCK_WOOD_PLACE;
            }

            @Override
            public Ingredient getRepairMaterial() {
                return Ingredient.fromStacks(new ItemStack(ModItems.WOODEN_PLATE.get()));
            }

            @Override
            public String getName() {
                return "woodenutilities:wood";
            }

            @Override
            public float getToughness() {
                return 0.0F;
            }

            @Override
            public float getKnockbackResistance() {
                return 0.0F;
            }

        };
    }
}
