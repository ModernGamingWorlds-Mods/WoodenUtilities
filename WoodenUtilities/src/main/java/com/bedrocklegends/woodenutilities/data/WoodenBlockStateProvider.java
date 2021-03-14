package com.bedrocklegends.woodenutilities.data;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.setup.WoodenBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class WoodenBlockStateProvider extends BlockStateProvider {
    public WoodenBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, WoodenUtilities.ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlock(WoodenBlocks.WOODEN_TANK.get(), this.models().cubeAll(Objects.requireNonNull(WoodenBlocks.WOODEN_TANK.get().getRegistryName()).toString(), loc("blocks/wooden_tank")));
        this.horizontalBlock(WoodenBlocks.RESIN_EXTRACTOR.get(), this.models().orientable(Objects.requireNonNull(WoodenBlocks.RESIN_EXTRACTOR.get().getRegistryName()).toString(), loc("blocks/resin_extractor"), loc("blocks/resin_extractor_front"), loc("blocks/resin_extractor")));
    }

    private ResourceLocation loc(String s){
        return new ResourceLocation(WoodenUtilities.ID, s);
    }
}
