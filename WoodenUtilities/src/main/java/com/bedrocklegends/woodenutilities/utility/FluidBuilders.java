package com.bedrocklegends.woodenutilities.utility;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.setup.WoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.WoodenFluids;
import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class FluidBuilders {
    private FluidBuilders() {
    }

    public static ForgeFlowingFluid.Properties getResinAttributes() {

        FluidAttributes.Builder attributes = FluidAttributes.builder(
                new ResourceLocation(WoodenUtilities.ID, "blocks/resin_still"),
                new ResourceLocation(WoodenUtilities.ID, "blocks/resin_flow"));

        return new ForgeFlowingFluid.Properties(WoodenFluids.RESIN, WoodenFluids.RESIN_FLOWING, attributes)
                .bucket(WoodenItems.RESIN_BUCKET)
                .block(WoodenBlocks.RESIN)
                .tickRate(60);
    }
}
