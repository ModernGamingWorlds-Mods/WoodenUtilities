package com.bedrocklegends.woodenutilities.capability;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class WoodenFluidHandler implements IFluidHandler {
    private FluidStack containedStack;
    private int capacity;
    private int ioSpeed;

    public WoodenFluidHandler(int capacity, int ioSpeed) {
        this.capacity = capacity;
        this.ioSpeed = ioSpeed;
        containedStack = FluidStack.EMPTY;
    }


    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        if (tank != 0) {
            return FluidStack.EMPTY;
        }
        return containedStack.copy();
    }

    @Override
    public int getTankCapacity(int tank) {
        if (tank != 0) {
            return 0;
        }
        return capacity;
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        if (tank != 0) {
            return false;
        }
        if (!containedStack.isEmpty()) {
            return true;
        }
        if (stack.isEmpty()){
            return false;
        }
        return stack.isFluidEqual(containedStack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (!this.isFluidValid(0, resource)) {
            return resource.getAmount();
        }

        if (resource.getAmount() > ioSpeed) {
            int remainder = resource.getAmount() - ioSpeed;
            return doFill(resource, remainder, action);
        }

        if (resource.getAmount() + containedStack.getAmount() > capacity) {
            int remainder = (resource.getAmount() + containedStack.getAmount()) - capacity;
            return doFill(resource, remainder, action);
        }

        return doFill(resource, 0, action);
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        if (!isFluidValid(0, resource)){
            return FluidStack.EMPTY;
        }

        if (resource.getAmount() > ioSpeed) {
            int remainder = resource.getAmount() - ioSpeed;
            return doDrain(resource, remainder, action);
        }


        if (containedStack.getAmount() - resource.getAmount() < 0) {
            int remainder = resource.getAmount() - containedStack.getAmount();
            return doDrain(resource, remainder, action);
        }

        return doDrain(resource, 0, action);
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return drain(new FluidStack(containedStack.getFluid(), maxDrain), action);
    }

    private int doFill(FluidStack resource, int remainder, FluidAction action) {
        if (action.simulate()) {
            return remainder;
        }   else if (action.execute()) {
            containedStack = new FluidStack(resource.getFluid(), resource.getAmount() + containedStack.getAmount() - remainder);
            return remainder;
        }
        return 0;
    }
    private FluidStack doDrain(FluidStack resource, int remainder, FluidAction action) {
        if (action.simulate()) {
            return new FluidStack(resource.getFluid(), resource.getAmount() - remainder);
        }   else if (action.execute()) {
            containedStack = new FluidStack(resource.getFluid(), resource.getAmount() - containedStack.getAmount() + remainder);
            return new FluidStack(resource.getFluid(), resource.getAmount() - remainder);
        }
        return FluidStack.EMPTY;
    }
}
