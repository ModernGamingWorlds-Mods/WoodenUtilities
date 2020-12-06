package com.bedrocklegends.woodenutilities.impl.baubles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IRing {

    default void onWorn(PlayerEntity playerEntity, ItemStack stack) {
    }

    default void onEquipped(PlayerEntity playerEntity, ItemStack stack) {
    }

    default void onUnequipped(PlayerEntity playerEntity, ItemStack stack) {
    }
}
