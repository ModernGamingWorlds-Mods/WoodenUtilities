package com.lazy.woodenutilities.inventory.containers;

import com.google.common.collect.Lists;
import com.lazy.woodenutilities.Configs;
import com.lazy.woodenutilities.content.ModBlocks;
import com.lazy.woodenutilities.content.ModContainers;
import com.lazy.woodenutilities.content.ModRecipeTypes;
import com.lazy.woodenutilities.inventory.slot.AxeSlot;
import com.lazy.woodenutilities.proxy.CommonProxy;
import com.lazy.woodenutilities.util.WoodCutterRecipe;
import com.lazy.woodenutilities.util.WoodCutterUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;

import java.util.List;

public class WoodCutterContainer extends Container {

    private IWorldPosCallable worldPosCallable;
    private IntReferenceHolder selectedRecipe = IntReferenceHolder.single();
    private World world;
    private Runnable inventoryUpdateListener = () -> {};

    public final IInventory inputInventory = new Inventory(1) {
        public void markDirty() {
            super.markDirty();
            WoodCutterContainer.this.onCraftMatrixChanged(this);
            WoodCutterContainer.this.inventoryUpdateListener.run();
        }
    };

    public final IInventory axeInv = new Inventory(1){
        @Override
        public void markDirty() {
            super.markDirty();
            WoodCutterContainer.this.onCraftMatrixChanged(this);
            WoodCutterContainer.this.inventoryUpdateListener.run();
        }
    };
    private CraftResultInventory inventory = new CraftResultInventory();

    private List<WoodCutterRecipe> jsonRecipes = Lists.newArrayList();
    private List<Item> plankVariants = Lists.newArrayList();
    private List<Item> allRecipes = Lists.newArrayList();

    private ItemStack itemStackInput = ItemStack.EMPTY;

    public AxeSlot axeSlot;
    private Slot inputInventorySlot;
    private Slot outputInventorySlot;

    private long lastOnTake;

    public WoodCutterContainer(int windowIdIn, PlayerInventory playerInventoryIn) {
        this(windowIdIn, playerInventoryIn, IWorldPosCallable.DUMMY);
    }

    public WoodCutterContainer(int windowIdIn, PlayerInventory playerInventoryIn, final IWorldPosCallable worldPosCallableIn) {
        super(ModContainers.WOOD_CUTTER_CONTAINER, windowIdIn);
        this.worldPosCallable = worldPosCallableIn;
        this.world = playerInventoryIn.player.world;
        this.inputInventorySlot = this.addSlot(new Slot(this.inputInventory, 0, 18, 20));
        this.axeSlot = (AxeSlot) this.addSlot(new AxeSlot(this.axeInv, 0, 18, 48) {
            @Override
            public void onSlotChanged() {
                super.onSlotChanged();
                WoodCutterContainer.this.onCraftMatrixChanged(WoodCutterContainer.this.axeInv);
            }
        });
        if (!Configs.NEED_AXE.get()) {
            this.axeSlot.setEnabled(false);
        }
        this.outputInventorySlot = this.addSlot(new Slot(this.inventory, 1, 143, 33) {
            public boolean isItemValid(ItemStack stack) {
                return false;
            }

            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                ItemStack itemstack = WoodCutterContainer.this.inputInventorySlot.decrStackSize(1);

                if(Configs.NEED_AXE.get()){
                    WoodCutterContainer.this.axeSlot.getStack().damageItem(1, thePlayer, (stackIn)->{
                        stackIn.sendBreakAnimation(thePlayer.getActiveHand());
                    });
                }

                if (!itemstack.isEmpty()) {
                    WoodCutterContainer.this.updateRecipeResultSlot();
                }

                stack.getItem().onCreated(stack, thePlayer.world, thePlayer);
                worldPosCallableIn.consume((world, blockPos) -> {
                    long l = world.getGameTime();
                    if (WoodCutterContainer.this.lastOnTake != l) {
                        world.playSound(null, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        WoodCutterContainer.this.lastOnTake = l;
                    }

                });
                return super.onTake(thePlayer, stack);
            }
        });

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
        }

        this.trackInt(this.selectedRecipe);
    }

    @OnlyIn(Dist.CLIENT)
    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    @OnlyIn(Dist.CLIENT)
    public List<Item> getRecipeList() {
        return this.allRecipes;
    }

    @OnlyIn(Dist.CLIENT)
    public int getRecipeListSize() {
        return this.allRecipes.size();
    }

    @OnlyIn(Dist.CLIENT)
    public boolean hasItemsInInputSlot() {
        return Configs.NEED_AXE.get() ? !this.axeSlot.getStack().isEmpty() && this.inputInventorySlot.getHasStack() && !this.allRecipes.isEmpty() : this.inputInventorySlot.getHasStack() && !this.allRecipes.isEmpty();
    }

    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(this.worldPosCallable, playerIn, ModBlocks.WOOD_CUTTER.get());
    }

    public boolean enchantItem(PlayerEntity playerIn, int id) {
        if (id >= 0 && id < this.allRecipes.size()) {
            this.selectedRecipe.set(id);
            this.updateRecipeResultSlot();
        }

        return true;
    }

    public void onCraftMatrixChanged(IInventory inventoryIn) {
        ItemStack itemstack = this.inputInventorySlot.getStack();
        if (itemstack.getItem() != this.itemStackInput.getItem()) {
            this.itemStackInput = itemstack.copy();
            this.updateAvailableRecipes(inventoryIn, itemstack);
        }
    }

    private void updateAvailableRecipes(IInventory inventoryIn, ItemStack stack) {
        //Clear the current recipe list
        this.clearRecipes();
        //Clear the current recipe and output slot
        this.selectedRecipe.set(-1);
        this.outputInventorySlot.putStack(ItemStack.EMPTY);
        //Get all recipes for the item stack in the input slot
        if (!stack.isEmpty()) {
            if(stack.getItem().getRegistryName() != null){
                //If the current item in the input slot is planks get the planks variants (eg stairs, slabs...)
                String registryName = stack.getItem().getRegistryName().toString();
                boolean isPlank = registryName.contains("planks");
                if(isPlank){
                    this.plankVariants = WoodCutterUtils.getWoodVariants().get(stack.getItem().getRegistryName().getPath().replace("_planks",""));
                }
                this.jsonRecipes = this.world.getRecipeManager().getRecipes(ModRecipeTypes.WOOD_CUTTER, inventoryIn, this.world);
                if(Configs.NEED_AXE.get() && this.axeSlot.getStack().isEmpty()) return;
                if(this.axeSlot.getToolItem().getTier().getHarvestLevel() < Configs.MINIMUM_AXE_TIER.get()) return;
                this.jsonRecipes.forEach((woodCutterRecipe)->{
                    Item block = woodCutterRecipe.getCraftingResult(inventoryIn).getItem();
                    if (ModList.get().isLoaded(woodCutterRecipe.modId)){
                        if(!this.plankVariants.contains(block)){
                            this.allRecipes.add(block);
                        }
                    }
                });
                if(plankVariants != null) this.allRecipes.addAll(this.plankVariants);
            }
        }
    }

    private void updateRecipeResultSlot() {
        if (!this.allRecipes.isEmpty()) {
            Item blockResult = this.allRecipes.get(this.selectedRecipe.get());
            this.outputInventorySlot.putStack(new ItemStack(blockResult));
        } else {
            this.outputInventorySlot.putStack(ItemStack.EMPTY);
        }

        this.detectAndSendChanges();
    }

    public void clearRecipes(){
        this.plankVariants.clear();
        this.jsonRecipes.clear();
        this.allRecipes.clear();
    }

    public ContainerType<?> getType() {
        return ModContainers.WOOD_CUTTER_CONTAINER;
    }

    @OnlyIn(Dist.CLIENT)
    public void setInventoryUpdateListener(Runnable listenerIn) {
        this.inventoryUpdateListener = listenerIn;
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
            } else if (!this.world.getRecipeManager().getRecipes(IRecipeType.STONECUTTING, new Inventory(itemstack1), this.world).isEmpty()) {
                if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
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

    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.inventory.removeStackFromSlot(1);
        this.worldPosCallable.consume((world, blockPos) -> {
            this.clearContainer(playerIn, playerIn.world, this.inputInventory);
            this.clearContainer(playerIn, playerIn.world, this.axeInv);
        });
    }
}