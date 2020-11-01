package com.lazy.woodenutilities.event;

import com.lazy.baubles.api.BaublesApi;
import com.lazy.woodenutilities.Configs;
import com.lazy.woodenutilities.content.ModItems;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.math.BigDecimal;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerBreakEventListener {

    @SubscribeEvent
    public static void onBlockBreak(PlayerEvent.BreakSpeed event){
        if(ModList.get().isLoaded("baubles")){
            if(event.getState().getMaterial() == Material.WOOD){
                if(BaublesApi.isBaubleEquipped(event.getPlayer(), ModItems.THE_LUMBERJACK.get()) != -1){
                    event.setNewSpeed(BigDecimal.valueOf(Configs.RING_LUMBERJACK_SPEED.get()).floatValue());
                }
            }
        }
    }
}
