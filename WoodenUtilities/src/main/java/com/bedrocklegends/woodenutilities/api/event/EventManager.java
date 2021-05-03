package com.bedrocklegends.woodenutilities.api.event;

import com.bedrocklegends.woodenutilities.api.resin.ResinProvider;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;

import java.util.Random;

public class EventManager {

    public static boolean onExtractLiquidResin(World world, BlockPos pos) {
        return !MinecraftForge.EVENT_BUS.post(new ResinExtractorEvent.ExtractLiquidResin(world, pos));
    }

    public static void onExtractResinFromBlockPre(World world, BlockPos tilePos, BlockPos resinProviderPos, ResinProvider provider, int internalResinAmt) {
        MinecraftForge.EVENT_BUS.post(new ResinExtractorEvent.ExtractResin.Pre(world, tilePos, resinProviderPos, internalResinAmt, provider));
    }

    public static int onExtractResinFromBlockPost(World world, BlockPos tilePos, BlockPos resinProviderPos, ResinProvider provider, int internalResinAmt) {
        ResinExtractorEvent.ExtractResin.Post post = new ResinExtractorEvent.ExtractResin.Post(world, tilePos, resinProviderPos, internalResinAmt, provider);
        MinecraftForge.EVENT_BUS.post(post);
        return post.getInternalResin();
    }
}
