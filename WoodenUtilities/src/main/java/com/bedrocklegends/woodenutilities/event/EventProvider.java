package com.bedrocklegends.woodenutilities.event;

import com.bedrocklegends.woodenutilities.resin.ResinProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class EventProvider {

    public static boolean fireExtractLiquidResinAndIsCancelled(World world, BlockPos pos) {
        return !MinecraftForge.EVENT_BUS.post(new ResinExtractorEvent.ExtractLiquidResin(world, pos));
    }

    public static boolean fireExtractResinFromBlockPreAndShouldRunPost(World world, BlockPos tilePos, BlockPos resinProviderPos, ResinProvider provider, int internalResinAmt) {
        return !MinecraftForge.EVENT_BUS.post(new ResinExtractorEvent.ExtractResin.Pre(world, tilePos, resinProviderPos, internalResinAmt, provider));
    }

    public static int onExtractResinFromBlockPost(World world, BlockPos tilePos, BlockPos resinProviderPos, ResinProvider provider, int internalResinAmt) {
        ResinExtractorEvent.ExtractResin.Post post = new ResinExtractorEvent.ExtractResin.Post(world, tilePos, resinProviderPos, internalResinAmt, provider);
        MinecraftForge.EVENT_BUS.post(post);
        return post.getInternalResin();
    }
}
