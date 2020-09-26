package com.lazy.woodenutilities.proxy;

import com.google.common.collect.ImmutableList;
import com.lazy.woodenutilities.content.ModBlocks;
import com.lazy.woodenutilities.content.ModItems;
import com.lazy.woodenutilities.content.ModRecipeSerializers;
import com.lazy.woodenutilities.content.ModTiles;
import com.lazy.woodenutilities.util.WoodCutterUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonProxy {

    public static Map<String, List<Item>> WOOD_VARIANTS = new HashMap<>();

    public void init(){
        addListener(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public void addListener(IEventBus bus){
        ModBlocks.init(bus);
        ModTiles.TILES.register(bus);
        ModItems.init(bus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(bus);;
        WOOD_VARIANTS = WoodCutterUtils.getWoodVariants();
        bus.addListener(this::setupCommon);
    }

    public void setupCommon(FMLCommonSetupEvent e){

    }
}
