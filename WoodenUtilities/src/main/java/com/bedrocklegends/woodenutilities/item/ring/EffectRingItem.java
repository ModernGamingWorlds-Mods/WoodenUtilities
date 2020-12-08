package com.bedrocklegends.woodenutilities.item.ring;

import com.bedrocklegends.woodenutilities.item.WoodenRingItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent("Effects:").mergeStyle(TextFormatting.AQUA));
        for (EffectInstance effect : this.effects) {
            String effectName = effect.getPotion().getDisplayName().getString().concat(" " + this.intToRoman(effect.getAmplifier()));
            tooltip.add(new StringTextComponent(effectName).mergeStyle(TextFormatting.GRAY));
        }
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

    private String intToRoman(int integer) {
        switch (integer) {
            case 0:
                return "I";
            case 1:
                return "II";
            case 2:
                return "III";
            case 3:
                return "IV";
            case 4:
                return "V";
            case 5:
                return "VI";
            case 6:
                return "VII";
            case 7:
                return "VIII";
            case 8:
                return "IX";
            case 9:
                return "X";
        }
        return String.valueOf(integer);
    }
}
