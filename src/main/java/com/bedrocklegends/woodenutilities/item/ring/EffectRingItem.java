package com.bedrocklegends.woodenutilities.item.ring;

import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;

import java.util.Collections;
import java.util.List;

public class EffectRingItem extends WoodenRingItem {

    private final List<EffectInstance> effects;

    public EffectRingItem(List<EffectInstance> effects) {
        this.effects = effects;
    }

    public EffectRingItem(EffectInstance effect) {
        this(Collections.singletonList(effect));
    }

    @Override
    public void onEquipped(LivingEntity player, ItemStack stack) {
        if (player instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) player;
            for (EffectInstance effectInstance : this.effects) {
                if (playerEntity.getActivePotionEffect(effectInstance.getPotion()) == null)
                    playerEntity.addPotionEffect(effectInstance);
            }
        }
    }

    @Override
    public void onUnequipped(LivingEntity player, ItemStack stack) {
        if (player instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) player;
            for (EffectInstance effectInstance : this.effects) {
                if (playerEntity.getActivePotionEffect(effectInstance.getPotion()) != null)
                    playerEntity.removePotionEffect(effectInstance.getPotion());
            }
        }
    }
}
