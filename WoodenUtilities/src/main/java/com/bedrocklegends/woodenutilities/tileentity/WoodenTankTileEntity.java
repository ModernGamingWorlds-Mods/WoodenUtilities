package com.bedrocklegends.woodenutilities.tileentity;

import com.bedrocklegends.woodenutilities.capability.WoodenFluidHandler;
import com.bedrocklegends.woodenutilities.setup.WoodenTiles;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class WoodenTankTileEntity extends TileEntity {

    private final WoodenFluidHandler fluidHandler = new WoodenFluidHandler(8000, 100);
    private final LazyOptional<WoodenFluidHandler> fluidHandlerLO = LazyOptional.of(() -> fluidHandler);

    public WoodenTankTileEntity() {
        super(WoodenTiles.WOODEN_TANK.get());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return fluidHandlerLO.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        fluidHandlerLO.invalidate();
    }
}
