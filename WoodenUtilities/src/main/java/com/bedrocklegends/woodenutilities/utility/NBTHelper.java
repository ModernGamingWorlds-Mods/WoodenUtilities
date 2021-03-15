package com.bedrocklegends.woodenutilities.utility;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class NBTHelper {

    public static CompoundNBT writeBlockPosList(List<BlockPos> blockPosList) {
        CompoundNBT nbt = new CompoundNBT();
        int index = 0;
        for (BlockPos pos : blockPosList) {
            nbt.put(String.valueOf(index), NBTUtil.writeBlockPos(pos));
            index++;
        }
        return nbt;
    }

    public static List<BlockPos> readBlockPosList(CompoundNBT nbt) {
        List<BlockPos> blockPosList = new ArrayList<>();
        for (String s : nbt.getAllKeys()) {
            blockPosList.add(NBTUtil.readBlockPos(nbt.getCompound(s)));
        }
        return blockPosList;
    }
}
