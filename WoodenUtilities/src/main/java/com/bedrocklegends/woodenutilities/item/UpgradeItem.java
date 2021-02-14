package com.bedrocklegends.woodenutilities.item;

import com.bedrocklegends.woodenutilities.setup.WoodenItemGroup;
import com.bedrocklegends.woodenutilities.utility.IUpgradable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class UpgradeItem extends Item {

    public UpgradeItem() {
        super(new Properties().group(WoodenItemGroup.INSTANCE));
    }

    public abstract boolean onApply(IUpgradable upgradable, ItemStack upgrade);

    @Override
    @Nonnull
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockPos pos = context.getPos();
        World world = context.getWorld();

        if (world.isRemote) return ActionResultType.PASS;
        if (!world.getBlockState(pos).hasTileEntity() && !(world.getTileEntity(pos) instanceof IUpgradable))
            return ActionResultType.PASS;

        IUpgradable upgradable = (IUpgradable) world.getTileEntity(pos);
        if (upgradable == null) return ActionResultType.PASS;
        ItemStack uniqueCopy = context.getItem();
        uniqueCopy.setCount(1);
        boolean applyResult = this.onApply(upgradable, uniqueCopy);
        if (applyResult) {
            context.getItem().shrink(1);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
