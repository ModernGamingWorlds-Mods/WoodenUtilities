package com.bedrocklegends.woodenutilities;

import com.bedrocklegends.woodenutilities.api.event.PostSaplingGrowEvent;
import com.bedrocklegends.woodenutilities.api.resin.ResinProvider;
import com.bedrocklegends.woodenutilities.api.resin.ResinProviderManager;
import com.bedrocklegends.woodenutilities.setup.WoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.WoodenTags;
import com.bedrocklegends.woodenutilities.tile.ResinExtractorTile;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onTagUpdateComplete(TagsUpdatedEvent event) {
        for (Block block : BlockTags.LOGS.getAllElements()) {
            ResinProviderManager.add(new ResinProvider(10, block));
        }
    }

    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        event.getEntityLiving().handleFluidAcceleration(WoodenTags.RESIN, 0.05D);
    }

    @SubscribeEvent
    public static void onPostSaplingGrow(PostSaplingGrowEvent e) {
        for (Direction value : Direction.Plane.HORIZONTAL.getDirectionValues().collect(Collectors.toList())) {
            BlockPos atDir = e.getPos().offset(value);
            Block blockAtDir = e.getWorld().getBlockState(atDir).getBlock();
            if (blockAtDir == WoodenBlocks.RESIN_EXTRACTOR.get()) {
                ResinExtractorTile resinExtractor = (ResinExtractorTile) e.getWorld().getTileEntity(atDir);
                if (resinExtractor != null) {
                    if (resinExtractor.getFacingPos().equals(e.getPos())) {
                        resinExtractor.startWorking();
                        break;
                    }
                }
            }
        }
    }
}
