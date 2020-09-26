package com.lazy.woodenutilities.content;

import com.lazy.woodenutilities.WoodenUtilities;
import com.lazy.woodenutilities.tiles.WoodenFurnaceTileEntity;
import com.lazy.woodenutilities.tiles.WoodenHopperTileEntity;
import com.lazy.woodenutilities.tiles.WoodenSolarPanelTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTiles {

    public static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, WoodenUtilities.MOD_ID);

    public static final RegistryObject<TileEntityType<WoodenHopperTileEntity>> WOODEN_HOPPER_TILE = TILES.register("wooden_hopper",
            ()-> TileEntityType.Builder.create(WoodenHopperTileEntity::new, ModBlocks.WOODEN_HOPPER.get()).build(null));

    public static final RegistryObject<TileEntityType<WoodenFurnaceTileEntity>> WOODEN_FURNACE_TILE = TILES.register("wooden_furnace",
            ()-> TileEntityType.Builder.create(WoodenFurnaceTileEntity::new, ModBlocks.WOODEN_FURNACE.get()).build(null));

    public static final RegistryObject<TileEntityType<WoodenSolarPanelTileEntity>> WOODEN_SOLAR_PANEL = TILES.register("wooden_solar_panel",
            ()-> TileEntityType.Builder.create(WoodenSolarPanelTileEntity::new, ModBlocks.WOODEN_SOLAR_PANEL.get()).build(null));

}
