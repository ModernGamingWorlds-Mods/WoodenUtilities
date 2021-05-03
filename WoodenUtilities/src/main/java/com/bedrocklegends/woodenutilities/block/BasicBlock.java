package com.bedrocklegends.woodenutilities.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BasicBlock extends Block {
    private final Supplier<TileEntity> tileEntitySupplier;

    public BasicBlock(BlockBuilder builder) {
        super(builder.getProperties());
        this.tileEntitySupplier = builder.getTileEntitySupplier();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return tileEntitySupplier != null;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        if (tileEntitySupplier == null) {
            return null;
        } else {
            return tileEntitySupplier.get();
        }
    }
}
