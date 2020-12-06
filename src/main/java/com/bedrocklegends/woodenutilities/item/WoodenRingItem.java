package com.bedrocklegends.woodenutilities.item;

import com.bedrocklegends.woodenutilities.impl.baubles.IRing;
import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import com.lazy.baubles.api.bauble.BaubleType;
import com.lazy.baubles.api.bauble.IBauble;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class WoodenRingItem extends Item implements IBauble {

    private final IRing ring;
    private final List<String> tooltips;

    public WoodenRingItem(@Nullable IRing ring, List<String> tooltips) {
        super(new Properties().group(WoodenItems.WOODEN_UTILITIES_TAB).maxStackSize(1));
        this.ring = ring;
        this.tooltips = tooltips;
    }

    public WoodenRingItem(@Nullable IRing ring) {
        this(ring, Collections.emptyList());
    }

    public WoodenRingItem() {
        this(null, Collections.emptyList());
    }

    public WoodenRingItem(List<EffectInstance> effectInstances) {
        this(new IRing() {
            @Override
            public void onEquipped(PlayerEntity playerEntity, ItemStack stack) {
                for (EffectInstance effectInstance : effectInstances) {
                    if (playerEntity.getActivePotionEffect(effectInstance.getPotion()) == null)
                        playerEntity.addPotionEffect(effectInstance);
                }
            }

            @Override
            public void onUnequipped(PlayerEntity playerEntity, ItemStack stack) {
                for (EffectInstance effectInstance : effectInstances) {
                    if (playerEntity.getActivePotionEffect(effectInstance.getPotion()) != null)
                        playerEntity.removePotionEffect(effectInstance.getPotion());
                }
            }
        });
    }

    public WoodenRingItem(EffectInstance effectInstance) {
        this(Collections.singletonList(effectInstance));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltips.forEach(s -> tooltip.add(new StringTextComponent(s)));
    }

    @Override
    public void onWornTick(LivingEntity player, ItemStack stack) {
        if(this.ring == null) return;
        if (!(player instanceof PlayerEntity)) return;
        this.ring.onWorn((PlayerEntity) player, stack);
    }

    @Override
    public void onEquipped(LivingEntity player, ItemStack stack) {
        if(this.ring == null) return;
        if (!(player instanceof PlayerEntity)) return;
        this.ring.onEquipped((PlayerEntity) player, stack);
    }

    @Override
    public void onUnequipped(LivingEntity player, ItemStack stack) {
        if(this.ring == null) return;
        if (!(player instanceof PlayerEntity)) return;
        this.ring.onUnequipped((PlayerEntity) player, stack);
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.RING;
    }
}
