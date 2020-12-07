package com.bedrocklegends.woodenutilities;

import com.bedrocklegends.woodenutilities.setup.WoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import com.bedrocklegends.woodenutilities.setup.WoodenTiles;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WoodenUtilities.ID)
public class WoodenUtilities {
    public static final String ID = "woodenutilities";


    public WoodenUtilities() {
        WoodenBlocks.register();
        WoodenItems.register();
        WoodenTiles.register();
    }
}
