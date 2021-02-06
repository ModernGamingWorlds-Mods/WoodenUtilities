package com.bedrocklegends.woodenutilities;

import com.bedrocklegends.woodenutilities.setup.WoodenTags;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        event.getEntityLiving().handleFluidAcceleration(WoodenTags.RESIN, 0.05D);
    }
}
