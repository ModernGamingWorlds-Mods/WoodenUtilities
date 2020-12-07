package com.bedrocklegends.woodenutilities.block;

import com.bedrocklegends.woodenutilities.setup.WoodenTiles;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class WoodenTankBlock extends Block {
    public WoodenTankBlock() {
        super(AbstractBlock.Properties
                .create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(2, 3)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE)
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return WoodenTiles.WOODEN_TANK.get().create();
    }
}
