package com.lazy.woodenutilities.inventory.containers;

import com.lazy.woodenutilities.content.ModContainers;
import com.lazy.woodenutilities.content.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Objects;

public class WoodenBackpackContainer extends Container {

    private IInventory backpackInv;
    private ItemStack backpack;

    public WoodenBackpackContainer(int windowIdIn, PlayerInventory playerInventoryIn) {
        this(windowIdIn, playerInventoryIn, new Inventory(9), new ItemStack(ModItems.WOODEN_BACKPACK.get()));
    }

    public WoodenBackpackContainer(int windowIdIn, PlayerInventory playerInventoryIn, IInventory backpackInventory, ItemStack backpack) {
        super(ModContainers.WOODEN_BACKPACK_CONTAINER, windowIdIn);

        this.backpackInv = backpackInventory;
        this.backpack = backpack;

        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(backpackInventory, j, 8 + j * 18, 20) {
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
            itemstack = itemstack1.copy();

            if (index < 9) {
                if (!this.mergeItemStack(itemstack1, 8, this.inventorySlots.size(), true)) {
                    System.out.println(1);
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 8, false)) {
                System.out.println(2);
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    public NonNullList<ItemStack> getBackpackItems() {
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(this.backpackInv.getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < this.backpackInv.getSizeInventory(); i++) {
            itemStacks.set(i, this.backpackInv.getStackInSlot(i));
        }
        return itemStacks;
    }

    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        if (!playerIn.world.isRemote) {
            backpack.setTag(new CompoundNBT());
            ItemStackHelper.saveAllItems(Objects.requireNonNull(backpack.getTag()), this.getBackpackItems());
            for (int i = 0; i < playerIn.inventory.mainInventory.size(); ) {
                if (playerIn.inventory.mainInventory.get(i) == ItemStack.EMPTY) {
                    playerIn.inventory.mainInventory.set(i, backpack);
                    return;
                } else if (i == playerIn.inventory.mainInventory.size() - 1 && playerIn.inventory.mainInventory.get(playerIn.inventory.mainInventory.size() - 1) != ItemStack.EMPTY) {
                    Vector3d pos = playerIn.getPositionVec();
                    InventoryHelper.spawnItemStack(playerIn.world, pos.x, pos.y, pos.z, backpack);
                    return;
                } else {
                    i++;
                }
            }
        }
    }
}