package com.lazy.woodenutilities.content;

import com.lazy.woodenutilities.WoodenUtilities;
import com.lazy.woodenutilities.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WoodenUtilities.MOD_ID);

    public static RegistryObject<WoodenHopperBlock> WOODEN_HOPPER;
    public static RegistryObject<WoodenFurnaceBlock> WOODEN_FURNACE;
    public static RegistryObject<WoodCutterBlock> WOOD_CUTTER;
    public static RegistryObject<WoodSolarPanelBlock> WOODEN_SOLAR_PANEL;
    public static RegistryObject<CraftingSlabBlock> CRAFTING_SLAB;
    public static RegistryObject<WoodenLampBlock> WOODEN_LAMP;
    public static RegistryObject<WoodenTntBlock> WOODEN_TNT;

    public static void init(IEventBus bus) {
        WOODEN_HOPPER = BLOCKS.register("wooden_hopper", WoodenHopperBlock::new);
        WOODEN_FURNACE = BLOCKS.register("wooden_furnace", WoodenFurnaceBlock::new);
        WOOD_CUTTER = BLOCKS.register("wood_cutter", WoodCutterBlock::new);
        WOODEN_SOLAR_PANEL = BLOCKS.register("wooden_solar_panel", WoodSolarPanelBlock::new);
        CRAFTING_SLAB = BLOCKS.register("crafting_slab", CraftingSlabBlock::new);
        WOODEN_LAMP = BLOCKS.register("wooden_lamp", WoodenLampBlock::new);
        WOODEN_TNT = BLOCKS.register("wooden_tnt", WoodenTntBlock::new);

        BLOCKS.register(bus);
    }
}
