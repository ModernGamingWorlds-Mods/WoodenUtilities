package com.lazy.woodenutilities.item;

import com.lazy.woodenutilities.inventory.containers.WoodenBackpackContainer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class WoodenBackpack extends BasicWoodItem implements INamedContainerProvider {

    public IInventory self = new Inventory(9);

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        NonNullList<ItemStack> stacks = NonNullList.withSize(self.getSizeInventory(), ItemStack.EMPTY);
        if (!stack.hasTag()) stack.setTag(new CompoundNBT());
        if (stack.getTag().contains("Items")) {
            ItemStackHelper.loadAllItems(stack.getTag(), stacks);
        } else {
            ItemStackHelper.saveAllItems(stack.getTag(), stacks);
        }
        addContentToInv(stacks);
        playerIn.openContainer(this);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public void addContentToInv(NonNullList<ItemStack> stacks) {
        for (int i = 0; i < self.getSizeInventory(); i++) {
            self.setInventorySlotContents(i, stacks.get(i));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.hasTag()) {
            tooltip.add(new StringTextComponent("Items:"));
            NonNullList<ItemStack> stacks = NonNullList.withSize(9, ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(stack.getTag(), stacks);
            for (ItemStack stack1 : stacks) {
                if (stack1 != ItemStack.EMPTY)
                    tooltip.add(new StringTextComponent(stack1.getDisplayName().getString()).func_240702_b_(" x" + stack1.getCount()));
            }
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.wooden_backpack");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity p_createMenu_3_) {
        return new WoodenBackpackContainer(id, playerInventory, self);
    }
}
