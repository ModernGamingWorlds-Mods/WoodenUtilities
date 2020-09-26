package com.lazy.woodenutilities.inventory.containers;

import com.lazy.woodenutilities.content.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraftforge.energy.CapabilityEnergy;

public class WoodenSolarPanelContainer extends Container {
    private final IInventory solarPanelInventory;
    private final IIntArray data;
    private final Slot chargeSlot;

    public WoodenSolarPanelContainer(int id, PlayerInventory playerInventory, IIntArray data) {
        this(id, playerInventory, new Inventory(1), data);
    }

    public WoodenSolarPanelContainer(int id, PlayerInventory playerInv, IInventory tileInv, IIntArray data) {
        super(ModContainers.WOODEN_SOLAR_PANEL_CONTAINER, id);
        this.solarPanelInventory = tileInv;
        assertInventorySize(tileInv, 1);
        tileInv.openInventory(playerInv.player);

        this.chargeSlot = this.addSlot(new Slot(tileInv, 0, 134, 20){
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getCapability(CapabilityEnergy.ENERGY).isPresent();
            }
        });

        for(int l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInv, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInv, i1, 8 + i1 * 18, 109));
        }

        trackIntArray(data);
        this.data = data;
    }

    public int getMaxEnergy(){
        return this.data.get(1);
    }

    public int getEnergy(){
        return this.data.get(0);
    }

    public int getInput(){
        return this.data.get(2);
    }

    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.solarPanelInventory.isUsableByPlayer(playerIn);
    }

    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.solarPanelInventory.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.solarPanelInventory.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.solarPanelInventory.getSizeInventory(), false)) {
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

    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.solarPanelInventory.closeInventory(playerIn);
    }
}
