package com.lazy.woodenutilities.block;

import com.lazy.woodenutilities.content.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;

public class WoodenBarsBlock extends PaneBlock {

    public WoodenBarsBlock() {
        super(Properties.from(Blocks.OAK_PLANKS));
        ModItems.addBlockItem(this, "wooden_bars");
    }
}
