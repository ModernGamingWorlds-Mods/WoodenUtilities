package com.lazy.woodenutilities.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class InvSaver {

    public static void saveSingleItemInventory(CompoundNBT compoundNBT, ItemStack stack){
        CompoundNBT itemTag = new CompoundNBT();
        stack.write(itemTag);
        compoundNBT.put("Inventory", itemTag);
    }

    public static ItemStack loadSingleItemInventory(CompoundNBT compoundNBT){
        return ItemStack.read(compoundNBT.getCompound("Inventory"));
    }
}
