package com.bedrocklegends.woodenutilities.builder;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class BlockBuilder {
    private final AbstractBlock.Properties properties;
    private Supplier<TileEntity> tileEntitySupplier;

    private BlockBuilder(Material material) {
        this.properties = AbstractBlock.Properties.of(material);
    }

    public static BlockBuilder create(Material material) {
        return new BlockBuilder(material);
    }

    public BlockBuilder sound(SoundType soundType) {
        this.properties.sound(soundType);
        return this;
    }

    public BlockBuilder hardness(float hardness) {
        this.properties.strength(hardness);
        return this;
    }

    public BlockBuilder hardnessAndResistance(float hardness, float resistance) {
        this.properties.strength(hardness, resistance);
        return this;
    }

    public BlockBuilder harvestLevel(int level) {
        this.properties.harvestLevel(level);
        return this;
    }

    public BlockBuilder harvestTool(ToolType toolType) {
        this.properties.harvestTool(toolType);
        return this;
    }

    public Supplier<TileEntity> getTileEntitySupplier() {
        return tileEntitySupplier;
    }

    public AbstractBlock.Properties getProperties() {
        return properties;
    }

    public BlockBuilder tileEntitySupplier(Supplier<TileEntity> supplier) {
        this.tileEntitySupplier = supplier;
        return this;
    }
}
