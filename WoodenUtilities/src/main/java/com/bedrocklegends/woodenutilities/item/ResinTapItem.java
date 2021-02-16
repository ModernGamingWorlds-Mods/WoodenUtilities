package com.bedrocklegends.woodenutilities.item;

import com.bedrocklegends.woodenutilities.setup.WoodenFluids;
import com.bedrocklegends.woodenutilities.setup.WoodenItemGroup;
import com.google.common.base.Preconditions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class ResinTapItem extends AxeItem {

    public static final String TAG_CURRENT_RESIN = "CurrentResin";
    public static final int MAX_CAPACITY = 1000; //TODO: Config

    public ResinTapItem() {
        super(ItemTier.STONE, 1.5F, -3.0F, new Item.Properties().group(WoodenItemGroup.INSTANCE));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();

        if (!world.isRemote) {
            if (world.getTileEntity(pos) != null) {
                if (world.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, context.getFace()).isPresent()) {
                    world.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, context.getFace()).ifPresent(fluidHandler -> {
                        int remainder = fluidHandler.fill(new FluidStack(WoodenFluids.RESIN.get(), getCurrent(context.getItem())), FluidAction.EXECUTE);
                        setAmount(context.getItem(), remainder);
                    });
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent("Capacity: " + getCurrent(stack) + "/1000"));
    }

    public static void addResin(ItemStack stack, int amount) {
        Preconditions.checkNotNull(stack.getTag());
        if (!containsResin(stack)) setAmount(stack, amount);
        else setAmount(stack, Math.min(getCurrent(stack) + amount, MAX_CAPACITY));
    }

    public static void setAmount(ItemStack stack, int amount) {
        Preconditions.checkNotNull(stack.getTag());
        stack.getTag().putInt(TAG_CURRENT_RESIN, amount);
    }

    public static int getCurrent(ItemStack stack) {
        Preconditions.checkNotNull(stack.getTag());
        return stack.getTag().getInt(TAG_CURRENT_RESIN);
    }

    public static boolean canAdd(ItemStack stack) {
        Preconditions.checkNotNull(stack.getTag());
        if (!containsResin(stack)) setAmount(stack, 0);
        return getCurrent(stack) < MAX_CAPACITY;
    }

    private static boolean containsResin(ItemStack stack) {
        Preconditions.checkNotNull(stack.getTag());
        return stack.getTag().contains(TAG_CURRENT_RESIN);
    }
}
