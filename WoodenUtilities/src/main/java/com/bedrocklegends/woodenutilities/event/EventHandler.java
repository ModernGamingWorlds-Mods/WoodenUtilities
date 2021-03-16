package com.bedrocklegends.woodenutilities.event;

import com.bedrocklegends.woodenutilities.resin.ResinProvider;
import com.bedrocklegends.woodenutilities.resin.ResinProviderManager;
import com.bedrocklegends.woodenutilities.setup.SetupWoodenTags;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onTagUpdateComplete(TagsUpdatedEvent event) {
        for (Block block : BlockTags.LOGS.getValues()) {
            ResinProviderManager.add(new ResinProvider(10, block));
        }
    }

    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        event.getEntityLiving().updateFluidHeightAndDoFluidPushing(SetupWoodenTags.RESIN, 0.05D);
    }
}
