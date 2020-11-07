package com.lazy.woodenutilities.impl.baubles;

import com.lazy.baubles.api.BaubleType;
import com.lazy.baubles.api.BaublesApi;
import com.lazy.baubles.api.IBauble;
import com.lazy.baubles.api.cap.IBaublesItemHandler;
import com.lazy.woodenutilities.WoodenUtilities;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class WoodenRingItem extends Item implements IBauble {

    private final List<String> tooltips;
    private IRingWorn iRingWorn;

    public WoodenRingItem(List<String> tooltips, @Nullable IRingWorn iRingWorn) {
        super(new Properties().group(WoodenUtilities.WOODEN_UTILITIES).maxStackSize(1));
        this.tooltips = tooltips;
        this.iRingWorn = iRingWorn;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        for (String s : tooltips) {
            tooltip.add(new StringTextComponent(s));
        }
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        IBaublesItemHandler itemHandler = BaublesApi.getBaublesHandler(playerIn).orElseThrow(NullPointerException::new);
        if (itemHandler.getStackInSlot(1).isEmpty() || itemHandler.getStackInSlot(2).isEmpty()) {
            if (itemHandler.getStackInSlot(1).isEmpty()) {
                itemHandler.setStackInSlot(1, itemstack.copy());
            } else {
                itemHandler.setStackInSlot(2, itemstack.copy());
            }
            itemstack.setCount(0);
            return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
        } else {
            return ActionResult.resultFail(itemstack);
        }
    }

    @Override
    public void onWornTick(LivingEntity player, ItemStack stack) {
        if (iRingWorn == null) return;
        if (!(player instanceof PlayerEntity)) return;
        this.iRingWorn.onWorn((PlayerEntity) player, stack);
    }

    @Override
    public BaubleType getBaubleType() {
        return BaubleType.RING;
    }
}
