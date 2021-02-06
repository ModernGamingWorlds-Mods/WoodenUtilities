package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodenFluids {

    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, WoodenUtilities.ID);

    public static RegistryObject<FlowingFluid> RESIN = FLUIDS.register("resin", () -> new ForgeFlowingFluid.Source(getResinAttributes()));
    public static RegistryObject<FlowingFluid> RESIN_FLOWING = FLUIDS.register("resin_flowing", () -> new ForgeFlowingFluid.Flowing(getResinAttributes()));

    private static ForgeFlowingFluid.Properties getResinAttributes() {
        FluidAttributes.Builder attributes = FluidAttributes.builder(
                new ResourceLocation(WoodenUtilities.ID, "blocks/resin_still"),
                new ResourceLocation(WoodenUtilities.ID, "blocks/resin_flow"));

        return new ForgeFlowingFluid.Properties(RESIN, RESIN_FLOWING, attributes)
                .bucket(WoodenItems.RESIN_BUCKET)
                .block(WoodenBlocks.RESIN)
                .tickRate(60);
    }

    public static void register() {
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
