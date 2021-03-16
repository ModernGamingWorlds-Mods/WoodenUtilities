package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.builder.FluidBuilder;
import com.bedrocklegends.woodenutilities.util.Constants;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SetupWoodenFluids {

    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Constants.MODID);

    public static RegistryObject<FlowingFluid> RESIN = FLUIDS.register(Constants.Blocks.RESIN,
            () -> new ForgeFlowingFluid.Source(FluidBuilder.getResinAttributes()));
    public static RegistryObject<FlowingFluid> RESIN_FLOWING = FLUIDS.register(Constants.Blocks.RESIN_FLOWING,
            () -> new ForgeFlowingFluid.Flowing(FluidBuilder.getResinAttributes()));

    public static void register() {
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
