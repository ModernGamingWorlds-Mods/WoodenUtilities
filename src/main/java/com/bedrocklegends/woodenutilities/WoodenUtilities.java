package com.bedrocklegends.woodenutilities;

import com.bedrocklegends.woodenutilities.setup.WoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import net.minecraftforge.fml.common.Mod;

@Mod(WoodenUtilities.ID)
public class WoodenUtilities {
    public static final String ID = "woodenutilities";

    public WoodenUtilities() {
        WoodenBlocks.register();
        WoodenItems.register();
    }
}
