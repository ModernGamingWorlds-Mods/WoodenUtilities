package com.bedrocklegends.woodenutilities.item.ring;

import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ForgedRingItem extends WoodenRingItem {

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("ring_tooltip.forged_ring"));
    }

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
