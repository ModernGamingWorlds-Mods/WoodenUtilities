package com.bedrocklegends.woodenutilities.item;

import com.bedrocklegends.woodenutilities.setup.WoodenItemGroup;
import com.bedrocklegends.woodenutilities.tileentity.UpgradableTileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class UpgradeItem extends Item {

    public UpgradeItem() {
        super(new Properties().tab(WoodenItemGroup.INSTANCE));
    }

    public abstract boolean onApply(UpgradableTileEntity upgradable, ItemStack upgrade);

    @Override
    @Nonnull
    public ActionResultType useOn(ItemUseContext context) {
        BlockPos pos = context.getClickedPos();
        World world = context.getLevel();

        if (world.isClientSide) return ActionResultType.PASS;
        if (!world.getBlockState(pos).hasTileEntity() && !(world.getBlockEntity(pos) instanceof UpgradableTileEntity))
            return ActionResultType.PASS;

        UpgradableTileEntity upgradable = (UpgradableTileEntity) world.getBlockEntity(pos);
        if (upgradable == null) return ActionResultType.PASS;
        ItemStack uniqueCopy = context.getItemInHand();
        uniqueCopy.setCount(1);
        boolean applyResult = this.onApply(upgradable, uniqueCopy);
        if (applyResult) {
            context.getItemInHand().shrink(1);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
