package com.bedrocklegends.woodenutilities.api;

import com.bedrocklegends.woodenutilities.resin.ResinProvider;
import com.bedrocklegends.woodenutilities.util.Constants;

import net.minecraft.block.Block;

public class FluidsAPI {
    /**
     * Gets the Resin provider through the Resin Provider Registry.
     *
     * @param block to check if the registry providers match the block.
     * @return if exists a Resin Provider for the given block returns that Resin Provider else returns null.
     */
    public static ResinProvider getResinProviderFor(Block block) {
        return Constants.Resin.RESIN_PROVIDERS.stream()
                .filter(rp -> rp.getResinProvider() == block)
                .findFirst()
                .orElse(null);
    }
}