package com.bedrocklegends.woodenutilities.config;


import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.config.holder.CommonConfigHolder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = WoodenUtilities.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigSubscriber {
    @SubscribeEvent
    public static void onConfigEvent(ModConfig.ModConfigEvent event) {
        CommonConfigHolder.init();
    }
}
