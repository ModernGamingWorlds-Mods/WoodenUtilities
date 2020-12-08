package com.bedrocklegends.woodenutilities.item.ring;

import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class FlightRingItem extends WoodenRingItem {

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("ring_tooltip.flight_ring"));
    }

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
