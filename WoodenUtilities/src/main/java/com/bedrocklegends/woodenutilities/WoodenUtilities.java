package com.bedrocklegends.woodenutilities;

import com.bedrocklegends.woodenutilities.config.ConfigHandler;
import com.bedrocklegends.woodenutilities.setup.SetupWoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.SetupWoodenFluids;
import com.bedrocklegends.woodenutilities.setup.SetupWoodenItems;
import com.bedrocklegends.woodenutilities.setup.SetupWoodenTags;
import com.bedrocklegends.woodenutilities.setup.SetupWoodenTileEntities;
import com.bedrocklegends.woodenutilities.util.Constants;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(Constants.MODID)
public class WoodenUtilities {
    public WoodenUtilities() {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHandler.spec);
    	
        SetupWoodenItems.register();
        SetupWoodenBlocks.register();
        SetupWoodenTileEntities.register();
        SetupWoodenFluids.register();
        SetupWoodenTags.register();
    }
}
