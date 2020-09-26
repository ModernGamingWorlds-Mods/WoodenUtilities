package com.lazy.woodenutilities.content;

import com.lazy.woodenutilities.WoodenUtilities;
import com.lazy.woodenutilities.inventory.containers.*;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = WoodenUtilities.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModContainers {

    public static List<ContainerType<?>> CONTAINERS = new ArrayList<>();

    @ObjectHolder("woodenutilities:wooden_hopper")
    public static ContainerType<WoodenHopperContainer> WOODEN_HOPPER_CONTAINER = createContainer("wooden_hopper", (id, inv, data) -> new WoodenHopperContainer(id, inv));
    @ObjectHolder("woodenutilities:wood_cutter")
    public static ContainerType<WoodCutterContainer> WOOD_CUTTER_CONTAINER = createContainer("wood_cutter", (id, inv, data) -> new WoodCutterContainer(id, inv));
    @ObjectHolder("woodenutilities:wooden_furnace")
    public static ContainerType<WoodenFurnaceContainer> WOODEN_FURNACE_CONTAINER = createContainer("wood_furnace", (id, inv, data) -> new WoodenFurnaceContainer(id, inv));
    @ObjectHolder("woodenutilities:wooden_solar_panel")
    public static ContainerType<WoodenSolarPanelContainer> WOODEN_SOLAR_PANEL_CONTAINER = createContainer("wooden_solar_panel", (id, inv, data) -> new WoodenSolarPanelContainer(id, inv, new IntArray(3)));
    @ObjectHolder("woodenutilities:wooden_backpack")
    public static ContainerType<WoodenBackpackContainer> WOODEN_BACKPACK_CONTAINER = createContainer("wooden_backpack", (id, inv, data) -> new WoodenBackpackContainer(id, inv));

    private static <T extends Container> ContainerType<T> createContainer(String name, IContainerFactory<T> factory) {
        ContainerType<T> containerType = IForgeContainerType.create(factory);
        containerType.setRegistryName(new ResourceLocation(WoodenUtilities.MOD_ID, name));
        CONTAINERS.add(containerType);
        return containerType;
    }

    @SubscribeEvent
    public static void onContainerRegister(final RegistryEvent.Register<ContainerType<?>> event) {
        event.getRegistry().registerAll(CONTAINERS.toArray(new ContainerType[0]));
    }
}
