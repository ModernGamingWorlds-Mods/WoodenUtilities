package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.utility.FluidBuilders;
import com.bedrocklegends.woodenutilities.utility.WoodenConstants;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodenFluids {

    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, WoodenUtilities.ID);

    public static RegistryObject<FlowingFluid> RESIN = FLUIDS.register(WoodenConstants.Blocks.RESIN,
            () -> new ForgeFlowingFluid.Source(FluidBuilders.getResinAttributes()));
    public static RegistryObject<FlowingFluid> RESIN_FLOWING = FLUIDS.register(WoodenConstants.Blocks.RESIN_FLOWING,
            () -> new ForgeFlowingFluid.Flowing(FluidBuilders.getResinAttributes()));

    public static void register() {
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
