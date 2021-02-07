package com.bedrocklegends.woodenutilities.mixin;

import com.bedrocklegends.woodenutilities.api.event.EventManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Tree.class)
public class TreeMixin {

    @Inject(at = @At(value = "RETURN", ordinal = 1), method = "attemptGrowTree(Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/world/gen/ChunkGenerator;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Ljava/util/Random;)Z")
    private void attemptGrowTree(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand, CallbackInfoReturnable<Boolean> infoReturnable) {
        EventManager.onPostSaplingGrow((Tree) (Object) this, world, rand, pos);
    }

}
