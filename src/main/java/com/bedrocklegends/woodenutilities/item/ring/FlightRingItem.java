package com.bedrocklegends.woodenutilities.item.ring;

import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class FlightRingItem extends WoodenRingItem {

    @Override
    public void onEquipped(LivingEntity player, ItemStack stack) {
        if(player instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)player;
            if (!playerEntity.abilities.allowFlying) playerEntity.abilities.allowFlying = true;
        }
    }

    @Override
    public void onUnequipped(LivingEntity player, ItemStack stack) {
        if(player instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)player;
            if (playerEntity.abilities.allowFlying) playerEntity.abilities.allowFlying = false;
            if (playerEntity.abilities.isFlying) playerEntity.abilities.isFlying = false;
        }
    }
}
