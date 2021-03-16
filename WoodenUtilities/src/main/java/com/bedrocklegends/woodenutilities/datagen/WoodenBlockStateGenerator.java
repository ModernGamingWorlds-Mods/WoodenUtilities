package com.bedrocklegends.woodenutilities.datagen;

import java.util.Objects;

import com.bedrocklegends.woodenutilities.setup.SetupWoodenBlocks;
import com.bedrocklegends.woodenutilities.util.WoodenConstants;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WoodenBlockStateGenerator extends BlockStateProvider {
    public WoodenBlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, WoodenConstants.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlock(SetupWoodenBlocks.WOODEN_TANK.get(), this.models().cubeAll(Objects.requireNonNull(SetupWoodenBlocks.WOODEN_TANK.get().getRegistryName()).toString(), loc("blocks/wooden_tank")));
        this.horizontalBlock(SetupWoodenBlocks.RESIN_EXTRACTOR.get(), this.models().orientable(Objects.requireNonNull(SetupWoodenBlocks.RESIN_EXTRACTOR.get().getRegistryName()).toString(), loc("blocks/resin_extractor"), loc("blocks/resin_extractor_front"), loc("blocks/resin_extractor")));
    }

    private ResourceLocation loc(String s){
        return new ResourceLocation(WoodenConstants.MODID, s);
    }
}
