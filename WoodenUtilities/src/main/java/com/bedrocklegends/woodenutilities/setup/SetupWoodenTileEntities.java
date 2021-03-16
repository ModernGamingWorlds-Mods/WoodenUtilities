package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.tileentity.ResinExtractorTileEntity;
import com.bedrocklegends.woodenutilities.tileentity.WoodenTankTileEntity;
import com.bedrocklegends.woodenutilities.util.Constants;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SetupWoodenTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister
            .create(ForgeRegistries.TILE_ENTITIES, Constants.MODID);

    public static final RegistryObject<TileEntityType<WoodenTankTileEntity>> WOODEN_TANK = TILES
            .register(Constants.Blocks.WOODEN_TANK, () -> TileEntityType.Builder
                    .of(WoodenTankTileEntity::new, SetupWoodenBlocks.WOODEN_TANK.get()).build(null));

    public static final RegistryObject<TileEntityType<ResinExtractorTileEntity>> RESIN_EXTRACTOR = TILES
            .register(Constants.Blocks.RESIN_EXTRACTOR, () -> TileEntityType.Builder
                    .of(ResinExtractorTileEntity::new, SetupWoodenBlocks.RESIN_EXTRACTOR.get()).build(null));

    public static void register() {
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
