package com.bedrocklegends.woodenutilities;

import com.bedrocklegends.woodenutilities.config.WoodenConfig;
import com.bedrocklegends.woodenutilities.setup.WoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import com.bedrocklegends.woodenutilities.setup.WoodenTiles;
import net.minecraftforge.fml.common.Mod;

@Mod(WoodenUtilities.ID)
public class WoodenUtilities {
    public static final String ID = "woodenutilities";


    public WoodenUtilities() {
        WoodenConfig.init();
        WoodenItems.register();
        WoodenBlocks.register();
        WoodenTiles.register();
    }
}
