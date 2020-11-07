package com.lazy.woodenutilities.impl.baubles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IRingWorn {

    void onWorn(PlayerEntity playerEntity, ItemStack stack);
}
