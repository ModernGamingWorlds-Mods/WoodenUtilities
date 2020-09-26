package com.lazy.woodenutilities.inventory.containers;

import com.google.common.collect.Lists;
import com.lazy.woodenutilities.Configs;
import com.lazy.woodenutilities.content.ModBlocks;
import com.lazy.woodenutilities.content.ModContainers;
import com.lazy.woodenutilities.content.ModItems;
import com.lazy.woodenutilities.content.ModRecipeTypes;
import com.lazy.woodenutilities.inventory.slot.AxeSlot;
import com.lazy.woodenutilities.item.WoodenBackpack;
import com.lazy.woodenutilities.util.WoodCutterRecipe;
import com.lazy.woodenutilities.util.WoodCutterUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class WoodenBackpackContainer extends Container {

    private IInventory backpackInv;

    public WoodenBackpackContainer(int windowIdIn, PlayerInventory playerInventoryIn) {
        this(windowIdIn, playerInventoryIn, new Inventory(9));
    }

    public WoodenBackpackContainer(int windowIdIn, PlayerInventory playerInventoryIn, IInventory backpackInventory) {
        super(ModContainers.WOODEN_BACKPACK_CONTAINER, windowIdIn);

        this.backpackInv = backpackInventory;

        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(backpackInventory, j, 8 + j * 18, 20){
                @Override
                public boolean isItemValid(ItemStack stack) {
                    return stack.getItem() != ModItems.WOODEN_BACKPACK.get();
                }
            });
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 109));
        }
    }

    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    public ContainerType<?> getType() {
        return ModContainers.WOODEN_BACKPACK_CONTAINER;
    }

    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return false;
    }

    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();
            if (index == 1) {
                item.onCreated(itemstack1, playerIn.world, playerIn);
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index == 0) {
                if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 2 && index < 29) {
                if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 29 && index < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }

            slot.onSlotChanged();
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
            this.detectAndSendChanges();
        }

        return itemstack;
    }

    public NonNullList<ItemStack> getBackpackItems(){
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(this.backpackInv.getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < this.backpackInv.getSizeInventory(); i++) {
            itemStacks.set(i, this.backpackInv.getStackInSlot(i));
        }
        return itemStacks;
    }

    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        ItemStack backpack = playerIn.getHeldItem(Hand.MAIN_HAND);
        if(backpack.getItem() == ModItems.WOODEN_BACKPACK.get()){
            if(!backpack.hasTag()) backpack.setTag(new CompoundNBT());
            ItemStackHelper.saveAllItems(backpack.getTag(), getBackpackItems());
        }
    }
}