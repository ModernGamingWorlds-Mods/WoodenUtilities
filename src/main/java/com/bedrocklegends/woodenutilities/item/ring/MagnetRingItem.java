package com.bedrocklegends.woodenutilities.item.ring;

import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class MagnetRingItem extends WoodenRingItem {

    @Override
    public void onWornTick(LivingEntity player, ItemStack stack) {
        if(player instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) player;
            List<ItemEntity> nearbyItemEntities = playerEntity.world.getEntitiesWithinAABB(ItemEntity.class, AxisAlignedBB.fromVector(playerEntity.getPositionVec()).grow(5));
            for (ItemEntity item : nearbyItemEntities) {
                item.setPosition(playerEntity.getPositionVec().x, playerEntity.getPositionVec().y, playerEntity.getPositionVec().z);
            }
        }
    }
}
