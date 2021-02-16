package com.bedrocklegends.woodenutilities;

import com.bedrocklegends.woodenutilities.api.APIUtils;
import com.bedrocklegends.woodenutilities.api.WoodenUtilitiesAPI;
import com.bedrocklegends.woodenutilities.api.event.PostSaplingGrowEvent;
import com.bedrocklegends.woodenutilities.api.resin.ResinProvider;
import com.bedrocklegends.woodenutilities.item.ResinTapItem;
import com.bedrocklegends.woodenutilities.setup.WoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import com.bedrocklegends.woodenutilities.setup.WoodenTags;
import com.bedrocklegends.woodenutilities.tile.ResinExtractorTile;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onTagUpdateComplete(TagsUpdatedEvent event) {
        for (Block block : BlockTags.LOGS.getAllElements()) {
            WoodenUtilitiesAPI.RESIN_PROVIDERS.add(new ResinProvider(10, block));
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

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent e) {
        IWorld world = e.getWorld();
        PlayerEntity playerEntity = e.getPlayer();
        ItemStack stack = e.getPlayer().getHeldItem(Hand.MAIN_HAND);

        if (!world.isRemote()) {
            if (stack.getItem() == WoodenItems.RESIN_TAP.get()){
                ResinProvider resinProvider = APIUtils.getResinProviderFor(e.getState().getBlock());
                if(resinProvider != null){
                    if(ResinTapItem.canAdd(stack)){
                        e.setCanceled(true);
                        world.destroyBlock(e.getPos(), false);
                        stack.damageItem(1, playerEntity, (stackIn) -> stackIn.sendBreakAnimation(Hand.MAIN_HAND));
                        ResinTapItem.addResin(stack, resinProvider.getAmount());
                    }
                }
            }
        }
    }
}
