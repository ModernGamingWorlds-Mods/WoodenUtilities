package com.bedrocklegends.woodenutilities.item.ring;

import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class RepairRingItem extends WoodenRingItem {

    @Override
    public void onWornTick(LivingEntity player, ItemStack stack) {
        if(player instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity)player;
            for (ItemStack itemStack : playerEntity.inventory.armorInventory) {
                if (!itemStack.isEmpty() && itemStack.isDamaged()) {
                    if (playerEntity.experienceLevel >= 0 && playerEntity.experienceTotal > 0) {
                        itemStack.setDamage(itemStack.getDamage() - 1);
                        playerEntity.giveExperiencePoints(-1);
                    }
                }
            }
        }
    }
}
