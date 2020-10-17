package com.lazy.woodenutilities.block;

import com.lazy.woodenutilities.Configs;
import com.lazy.woodenutilities.content.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TNTBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class WoodenTntBlock extends TNTBlock {

    public WoodenTntBlock() {
        super(Block.Properties.from(Blocks.TNT));
        ModItems.addBlockItem(this, "wooden_tnt");
    }

    @Override
    public void catchFire(BlockState state, World world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter);
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (oldState.getBlock() != state.getBlock()) {
            if (worldIn.isBlockPowered(pos)) {
                catchFire(state, worldIn, pos, null, null);
                worldIn.removeBlock(pos, false);
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.isBlockPowered(pos)) {
            catchFire(state, worldIn, pos, null, null);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote() && !player.isCreative() && state.get(UNSTABLE)) {
            catchFire(state, worldIn, pos, null, null);
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        if (!worldIn.isRemote) {
            TNTEntity tntentity = new TNTEntity(worldIn, ((float) pos.getX() + 0.5F), pos.getY(), ((float) pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy()) {
                @Override
                protected void explode() {
                    Vector3d pos = this.getPositionVec();
                    this.world.createExplosion(this, pos.x, pos.y + (double) (this.getHeight() / 16.0F), pos.z, Configs.TNT_POWER.get().floatValue(), Explosion.Mode.BREAK);
                }
            };
            tntentity.setFuse((short) (worldIn.rand.nextInt(tntentity.getFuse() / 4) + tntentity.getFuse() / 8));
            worldIn.addEntity(tntentity);
        }
    }

    private static void explode(World worldIn, BlockPos pos, @Nullable LivingEntity entityIn) {
        if (!worldIn.isRemote) {
            TNTEntity tntentity = new TNTEntity(worldIn, ((float) pos.getX() + 0.5F), pos.getY(), ((float) pos.getZ() + 0.5F), entityIn) {
                @Override
                protected void explode() {
                    Vector3d pos = this.getPositionVec();
                    this.world.createExplosion(this, pos.x, pos.y + (double) (this.getHeight() / 16.0F), pos.z, Configs.TNT_POWER.get().floatValue(), Explosion.Mode.BREAK);
                }
            };
            worldIn.addEntity(tntentity);
            worldIn.playSound(null, tntentity.getPositionVec().x, tntentity.getPositionVec().y, tntentity.getPositionVec().z, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        Item item = itemstack.getItem();
        if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        } else {
            catchFire(state, worldIn, pos, hit.getFace(), player);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            if (item == Items.FLINT_AND_STEEL) {
                itemstack.damageItem(1, player, (stack) -> {
                    stack.sendBreakAnimation(handIn);
                });
            } else {
                itemstack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        }
    }

    @Override
    public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
        if (!worldIn.isRemote) {
            Entity entity = projectile.getEntity();
            if (projectile.isBurning()) {
                BlockPos blockpos = hit.getPos();
                catchFire(state, worldIn, blockpos, null, entity instanceof LivingEntity ? (LivingEntity)entity : null);
                worldIn.removeBlock(blockpos, false);
            }
        }
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosionIn) {
        return false;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(UNSTABLE);
    }
}