package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.block.BasicBlock;
import com.bedrocklegends.woodenutilities.block.BlockBuilder;
import com.bedrocklegends.woodenutilities.block.WoodenTankBlock;
import com.bedrocklegends.woodenutilities.tile.WoodenTankTile;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodenBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister
        .create(ForgeRegistries.BLOCKS, WoodenUtilities.ID);
    public static final RegistryObject<BasicBlock> WOODEN_TANK = BLOCKS
        .register("wooden_tank", () -> new WoodenTankBlock(BlockBuilder.create(Material.WOOD).sound(SoundType.WOOD)
            .hardnessAndResistance(2.0F, 3.0F).harvestTool(ToolType.AXE).harvestLevel(0)
            .tileEntitySupplier(WoodenTankTile::new)));

    private WoodenBlocks() {
    }

    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
