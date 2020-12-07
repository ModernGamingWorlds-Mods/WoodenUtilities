package com.bedrocklegends.woodenutilities.item.ring;

import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class MagnetRingItem extends WoodenRingItem {

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("ring_tooltip.magnet_ring"));
    }

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
