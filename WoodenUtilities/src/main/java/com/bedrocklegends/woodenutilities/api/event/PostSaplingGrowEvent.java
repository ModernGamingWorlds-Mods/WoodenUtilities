package com.bedrocklegends.woodenutilities.api.event;

import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.Event;

import java.util.Random;

/**
 * Event that fire only when the Tree was successfully placed.
 * This event can't be cancelled.
 */
public class PostSaplingGrowEvent extends Event {

    private final Tree tree;
    private final ServerWorld world;
    private final Random random;
    private final BlockPos pos;

    public PostSaplingGrowEvent(Tree tree, ServerWorld world, Random rand, BlockPos pos) {
        this.tree = tree;
        this.world = world;
        this.random = rand;
        this.pos = pos;
    }

    public Tree getTree() {
        return this.tree;
    }

    public ServerWorld getWorld() {
        return this.world;
    }

    public Random getRandom() {
        return this.random;
    }

    public BlockPos getPos() {
        return this.pos;
    }
}
