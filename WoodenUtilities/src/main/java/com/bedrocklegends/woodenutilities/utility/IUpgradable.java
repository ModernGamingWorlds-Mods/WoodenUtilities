package com.bedrocklegends.woodenutilities.utility;

import com.bedrocklegends.woodenutilities.config.CommonConfig;
import com.bedrocklegends.woodenutilities.config.WoodenConfig;
import com.bedrocklegends.woodenutilities.item.UpgradeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.stream.Collectors;

public interface IUpgradable {

    TileEntity getUpgradableTile();

    int getNumOfUpgrades();

    void setNumOfUpgrades(int amount);

    List<ItemStack> getUpgrades();

    default boolean addUpgrade(ItemStack stack) {
        if (this.getNumOfUpgrades() == this.getMaxUpgrades()) return false;
        if (stack == null) return false;
        this.getUpgrades().add(stack);
        this.setNumOfUpgrades(this.getNumOfUpgrades() + 1);
        return true;
    }

    default List<ItemStack> getUpgradesFor(ItemStack toCheckFor){
        return this.getUpgrades().stream().filter(s-> s.getItem() == toCheckFor.getItem()).collect(Collectors.toList());
    }

    default <T extends UpgradeItem> List<ItemStack> getUpgradesFor(RegistryObject<T> toCheckFor){
        return this.getUpgradesFor(new ItemStack(toCheckFor.get()));
    }

    default boolean hasUpgrade(ItemStack stack) {
        return this.getUpgrades().stream().anyMatch(s -> s.getItem() == stack.getItem());
    }

    default <T extends UpgradeItem> boolean hasUpgrade(RegistryObject<T> toCheck) {
        return this.hasUpgrade(new ItemStack(toCheck.get()));
    }

    default int getMaxUpgrades() {
        return WoodenConfig.COMMON_CONFIG.maxUpgrades.get();
    }
}
