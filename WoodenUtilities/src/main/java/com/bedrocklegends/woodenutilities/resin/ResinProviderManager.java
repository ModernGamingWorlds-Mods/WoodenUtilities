package com.bedrocklegends.woodenutilities.resin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;

import java.util.List;

public class ResinProviderManager {

    private static final List<ResinProvider> RESIN_PROVIDERS = Lists.newArrayList();

    public static boolean add(ResinProvider provider) {
        if (exists(provider)) return false;
        if (provider.getResinProvider().getBlock() == Blocks.AIR) return false;
        if (provider.getAmount() <= 0) return false;
        return RESIN_PROVIDERS.add(provider);
    }

    public static boolean addAll(List<ResinProvider> providers) {
        for (ResinProvider provider : providers) {
            return add(provider);
        }
        return true;
    }

    private static boolean exists(ResinProvider provider) {
        return RESIN_PROVIDERS.stream().anyMatch(rp -> rp.equals(provider));
    }

    public static ImmutableList<ResinProvider> getProviders() {
        return ImmutableList.copyOf(RESIN_PROVIDERS);
    }
}
