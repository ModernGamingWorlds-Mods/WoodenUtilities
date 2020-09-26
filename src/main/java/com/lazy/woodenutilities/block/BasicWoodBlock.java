package com.lazy.woodenutilities.block;

import com.lazy.woodenutilities.content.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class BasicWoodBlock extends Block {

    public BasicWoodBlock(String registryName, Properties properties) {
        super(properties);
        ModItems.addBlockItem(this, registryName);
    }

    public BasicWoodBlock(String registryName) {
        this(registryName, Properties.from(Blocks.OAK_PLANKS));
    }
}
