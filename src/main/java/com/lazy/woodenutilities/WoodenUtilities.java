package com.lazy.woodenutilities;

import com.lazy.woodenutilities.proxy.ClientProxy;
import com.lazy.woodenutilities.proxy.CommonProxy;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WoodenUtilities.MOD_ID)
public class WoodenUtilities {

    public static final String MOD_ID = "woodenutilities";
    public static final ItemGroup WOODEN_UTILITIES = new ItemGroup("wooden_utilities") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.ACACIA_WOOD);
        }
    };

    public WoodenUtilities() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configs.COMMON_CONFIG);
        DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new).init();
        Configs.load(Configs.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-common.toml"));
    }
}
