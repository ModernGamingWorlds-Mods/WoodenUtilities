package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.util.WoodenConstants;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;

public class SetupWoodenTags {

    public static ITag.INamedTag<Fluid> RESIN;

    public static void register(){
        RESIN = FluidTags.bind(WoodenConstants.Blocks.RESIN);
    }
}
