package com.bedrocklegends.woodenutilities.event;

import com.bedrocklegends.woodenutilities.resin.ResinProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public class ResinExtractorEvent extends Event {

    private final World world;
    private final BlockPos blockPos;

    public ResinExtractorEvent(World world, BlockPos blockPos) {
        this.world = world;
        this.blockPos = blockPos;
    }

    public World getWorld() {
        return this.world;
    }

    public BlockPos getPos() {
        return this.blockPos;
    }

    @Cancelable
    public static class ExtractResin extends ResinExtractorEvent {

        private int internalResin;
        private final ResinProvider resinProvider;
        private final BlockPos resinProviderPos;

        public ExtractResin(World world, BlockPos blockPos, BlockPos resinProviderPos, int internalResin, ResinProvider resinProvider) {
            super(world, blockPos);
            this.resinProviderPos = resinProviderPos;
            this.internalResin = internalResin;
            this.resinProvider = resinProvider;
        }

        public BlockPos getResinProviderPos() {
            return this.resinProviderPos;
        }

        public int getInternalResin() {
            return this.internalResin;
        }

        public void setInternalResin(int internalResin) {
            this.internalResin = internalResin;
        }

        public ResinProvider getResinProvider() {
            return this.resinProvider;
        }

        public static class Pre extends ExtractResin {

            public Pre(World world, BlockPos blockPos, BlockPos resinProviderPos, int internalResin, ResinProvider resinProvider) {
                super(world, blockPos, resinProviderPos, internalResin, resinProvider);
            }
        }

        public static class Post extends ExtractResin {

            public Post(World world, BlockPos blockPos, BlockPos resinProviderPos, int internalResin, ResinProvider resinProvider) {
                super(world, blockPos, resinProviderPos, internalResin, resinProvider);
            }
        }
    }

    @Cancelable
    public static class ExtractLiquidResin extends ResinExtractorEvent {

        public ExtractLiquidResin(World world, BlockPos blockPos) {
            super(world, blockPos);
        }
    }
}
