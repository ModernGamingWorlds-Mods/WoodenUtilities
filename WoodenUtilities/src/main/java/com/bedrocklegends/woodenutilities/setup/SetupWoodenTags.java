package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.util.Constants;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;

public class SetupWoodenTags {

    public static ITag.INamedTag<Fluid> RESIN;

    public static void register(){
        RESIN = FluidTags.bind(Constants.Blocks.RESIN);
    }
}
