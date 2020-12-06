package com.bedrocklegends.woodenutilities.impl.baubles;

import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import com.lazy.baubles.api.BaublesAPI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BaublesRingLogic {

    @SubscribeEvent
    public static void onBlockBreak(PlayerEvent.BreakSpeed event) {
        if (ModList.get().isLoaded("baubles")) {
            onHoldRing(event);
        }
    }

    private static void onHoldRing(PlayerEvent.BreakSpeed event) {
        BlockState state = event.getState();
        PlayerEntity playerEntity = event.getPlayer();

        if (BaublesAPI.isBaubleEquipped(playerEntity, WoodenItems.LUMBERJACK.get()) != -1) {
            if (state.getMaterial() == Material.WOOD) {
                //TODO: Add config
                event.setNewSpeed(10f);
            }
        }
        if (BaublesAPI.isBaubleEquipped(playerEntity, WoodenItems.MIDAS_TOUCH.get()) != -1) {
            onUseMidasTouch(event);
        }
    }

    private static void onUseMidasTouch(PlayerEvent.BreakSpeed event) {
        BlockState state = event.getState();
        PlayerEntity playerEntity = event.getPlayer();
        BlockPos pos = event.getPos();
        World world = playerEntity.world;

        if (world != null && !world.isRemote) {
            boolean isWood = state.getMaterial() == Material.WOOD;
            boolean isAir = state.isAir(world, pos);
            boolean isLiquid = state.getBlock() instanceof FlowingFluidBlock;
            boolean isBreakable = state.getBlockHardness(world, pos) >= 0;
            boolean isTileEntity = state.hasTileEntity();
            boolean hasInv = world.getTileEntity(pos) instanceof IInventory;

            if (!isWood && isBreakable && !isAir && !isLiquid && !isTileEntity && !hasInv) {
                List<Block> woodBlocks = ForgeRegistries.BLOCKS.getValues().stream()
                        .filter(block -> block.getRegistryName() != null)
                        .filter(block -> block.getRegistryName().getPath().endsWith("_log") || block.getRegistryName().getPath().contains("_planks"))
                        .collect(Collectors.toList());
                if (BaublesAPI.isBaubleEquipped(playerEntity, WoodenItems.MIDAS_TOUCH.get()) != -1) {
                    int rand = world.rand.nextInt(woodBlocks.size());
                    world.setBlockState(pos, woodBlocks.get(rand).getDefaultState(), 4);
                    event.setCanceled(true);
                }
            }
        }
    }
}