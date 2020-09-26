package com.lazy.woodenutilities.client.event;

import com.lazy.woodenutilities.WoodenUtilities;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WoodenUtilities.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TextureStitching {

    @SubscribeEvent
    public static void onEvent(TextureStitchEvent.Pre e) {
        if (!e.getMap().getBasePath().equals("textures")) return;
        e.addSprite(new ResourceLocation(WoodenUtilities.MOD_ID, "item/axe_slot_empty"));
    }
}
