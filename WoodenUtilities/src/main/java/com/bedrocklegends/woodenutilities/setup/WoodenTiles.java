package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.tile.WoodenTankTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodenTiles {
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister
        .create(ForgeRegistries.TILE_ENTITIES, WoodenUtilities.ID);

    public static final RegistryObject<TileEntityType<WoodenTankTile>> WOODEN_TANK = TILES
        .register("wooden_tank", () -> TileEntityType.Builder
            .create(WoodenTankTile::new, WoodenBlocks.WOODEN_TANK.get()).build(null));

    public static void register() {
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
