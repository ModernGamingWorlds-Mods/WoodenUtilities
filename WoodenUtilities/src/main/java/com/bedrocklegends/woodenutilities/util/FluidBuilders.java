package com.bedrocklegends.woodenutilities.util;

import com.bedrocklegends.woodenutilities.setup.SetupWoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.SetupWoodenFluids;
import com.bedrocklegends.woodenutilities.setup.SetupWoodenItems;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class FluidBuilders {
    private FluidBuilders() {
    }

    public static ForgeFlowingFluid.Properties getResinAttributes() {

        FluidAttributes.Builder attributes = FluidAttributes.builder(
                new ResourceLocation(WoodenConstants.MODID, "blocks/resin_still"),
                new ResourceLocation(WoodenConstants.MODID, "blocks/resin_flow"));

        return new ForgeFlowingFluid.Properties(SetupWoodenFluids.RESIN, SetupWoodenFluids.RESIN_FLOWING, attributes)
                .bucket(SetupWoodenItems.RESIN_BUCKET)
                .block(SetupWoodenBlocks.RESIN)
                .tickRate(60);
    }
}
