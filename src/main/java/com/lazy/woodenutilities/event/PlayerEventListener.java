package com.lazy.woodenutilities.event;

import com.lazy.baubles.api.BaublesApi;
import com.lazy.woodenutilities.Configs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventListener {

    @SubscribeEvent
    public static void onJoined(EntityJoinWorldEvent e) {
        if (!e.getWorld().isRemote) return;
        if (ModList.get().isLoaded(BaublesApi.MOD_ID)) return;
        if (!Configs.SHOW_BAUBLES_MSG.get()) return;
        if (e.getEntity() instanceof PlayerEntity) {
            e.getEntity().sendMessage(new StringTextComponent("Hi! We checked and you don't have Baubles installed! This mod uses Baubles to add rings with special effects! Give it a try. (You can disable this message in the config file)"), e.getEntity().getUniqueID());
        }
    }
}
