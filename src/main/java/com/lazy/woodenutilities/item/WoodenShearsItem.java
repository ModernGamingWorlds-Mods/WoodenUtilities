package com.lazy.woodenutilities.item;

import com.lazy.woodenutilities.Configs;
import com.lazy.woodenutilities.WoodenUtilities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WoodenShearsItem extends ShearsItem {

    public WoodenShearsItem() {
        super(new Item.Properties().maxDamage(100).group(WoodenUtilities.WOODEN_UTILITIES));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (container.attemptDamageItem(1, new Random(), null))
            return ItemStack.EMPTY;
        else
            return container;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stackIn, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (Configs.DROP_SHEARS_PRODUCTS.get()) {
            Block block = state.getBlock();
            if (isPresentOnTag(BlockTags.LEAVES, state) || block == Blocks.GRASS || block == Blocks.FERN || block == Blocks.DEAD_BUSH || block == Blocks.VINE) {
                Block.spawnAsEntity(worldIn, pos, new ItemStack(state.getBlock().asItem()));
            }
        }
        return super.onBlockDestroyed(stackIn, worldIn, state, pos, entityLiving);
    }

    public boolean isPresentOnTag(ITag.INamedTag<Block> tag, BlockState state) {
        for (Block block : tag.getAllElements()) {
            if (state.isIn(block)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return super.canHarvestBlock(blockIn);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {
        if(Configs.DROP_SHEARS_PRODUCTS.get()){
            return super.itemInteractionForEntity(stack, playerIn, entity, hand);
        }
        return ActionResultType.PASS;
    }
}
