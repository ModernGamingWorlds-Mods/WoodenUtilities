package com.lazy.woodenutilities.tiles;

import com.lazy.woodenutilities.Configs;
import com.lazy.woodenutilities.content.ModTiles;
import com.lazy.woodenutilities.inventory.containers.WoodenSolarPanelContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WoodenSolarPanelTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    public static final String TAG_BUFFER = "buffer";
    public int internalBuffer = 0;
    public static final String TAG_OUTPUT = "output";
    public int outputValue = Configs.SOLAR_PANEL_OUTPUT.get();
    public static final String TAG_INPUT = "input";
    public int inputValue = 0;
    public static final String TAG_MAX_CAPACITY = "max_capacity";
    public int maxCapacity = Configs.SOLAR_PANEL_MAX_CAPACITY.get();

    public int workTime = 0;
    public int maxWorkTime = Configs.SOLAR_PANEL_WORK_TIME.get();

    public LazyOptional<IEnergyStorage> ENERGY_STORAGE = LazyOptional.of(this::createEnergyStorage);
    public IIntArray data = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return internalBuffer;
                case 1:
                    return maxCapacity;
                case 2:
                    return inputValue;
            }
            return -1;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    internalBuffer = value;
                case 1:
                    maxCapacity = value;
                case 2:
                    inputValue = value;
            }
        }

        @Override
        public int size() {
            return 3;
        }
    };

    public IInventory tileInv = new Inventory(1);

    public WoodenSolarPanelTileEntity() {
        super(ModTiles.WOODEN_SOLAR_PANEL.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT nbt = super.write(compound);
        nbt.putInt(TAG_BUFFER, this.internalBuffer);
        nbt.putInt(TAG_INPUT, this.inputValue);
        nbt.putInt(TAG_OUTPUT, this.outputValue);
        nbt.putInt(TAG_MAX_CAPACITY, this.maxCapacity);
        return nbt;
    }

    @Override
    public void func_230337_a_(BlockState state, CompoundNBT compound) { //read
        super.func_230337_a_(state, compound);
        this.internalBuffer = compound.getInt(TAG_BUFFER);
        this.inputValue = compound.getInt(TAG_INPUT);
        this.outputValue = compound.getInt(TAG_OUTPUT);
        this.maxCapacity = compound.getInt(TAG_MAX_CAPACITY);
    }

    @Override
    public void tick() {
        if (world != null) {
            if (!world.isRemote) {
                if (this.internalBuffer < this.maxCapacity) {

                    this.inputValue = this.calculateInputValue(this.world);

                    if(this.workTime >= this.maxWorkTime){
                        this.internalBuffer += this.inputValue;
                        this.workTime = 0;
                    } else {
                        this.workTime++;
                    }

                    if (!tileInv.getStackInSlot(0).isEmpty())
                        this.getEnergyStorage().extractEnergy(this.outputValue, false);
                }
            }
        }
    }

    public int calculateInputValue(@Nonnull World world){
        if (world.func_230315_m_().hasSkyLight()) {
            int i = world.getLightFor(LightType.SKY, this.pos) - world.getSkylightSubtracted();
            float f = world.getCelestialAngleRadians(1.0F);

            if (i > 0) {
                float f1 = f < (float) Math.PI ? 0.0F : ((float) Math.PI * 2F);
                f = f + (f1 - f) * 0.2F;
                i = Math.round((float) i * MathHelper.cos(f));
            }

            i = MathHelper.clamp(i, 0, 15);
            return i;
        } else {
            return 0;
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityEnergy.ENERGY) {
            return ENERGY_STORAGE.cast();
        }
        return super.getCapability(cap);
    }

    public IEnergyStorage getEnergyStorage() {
        return this.getCapability(CapabilityEnergy.ENERGY).orElseThrow(NullPointerException::new);
    }

    @Nonnull
    public IEnergyStorage createEnergyStorage() {
        return new IEnergyStorage() {
            @Override
            public int receiveEnergy(int maxReceive, boolean simulate) {
                return 0;
            }

            @Override
            public int extractEnergy(int maxExtract, boolean simulate) {
                if (!this.canExtract()) return 0;
                int result = Math.max((this.getEnergyStored() - maxExtract), 0);
                if (!simulate) WoodenSolarPanelTileEntity.this.setInternalBuffer(result);
                return result;
            }

            @Override
            public int getEnergyStored() {
                return WoodenSolarPanelTileEntity.this.internalBuffer;
            }

            @Override
            public int getMaxEnergyStored() {
                return WoodenSolarPanelTileEntity.this.maxCapacity;
            }

            @Override
            public boolean canExtract() {
                return true;
            }

            @Override
            public boolean canReceive() {
                return false;
            }
        };
    }

    public void setInternalBuffer(int internalBuffer) {
        this.internalBuffer = internalBuffer;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.wooden_solar_panel");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity playerEntity) {
        return new WoodenSolarPanelContainer(id, playerInv, tileInv, data);
    }
}
