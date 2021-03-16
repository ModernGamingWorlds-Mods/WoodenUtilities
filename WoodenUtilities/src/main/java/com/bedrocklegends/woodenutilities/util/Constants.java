package com.bedrocklegends.woodenutilities.util;

import com.bedrocklegends.woodenutilities.resin.ResinProvider;
import com.bedrocklegends.woodenutilities.resin.ResinProviderManager;
import com.google.common.collect.ImmutableList;

public class Constants {
	public static final String MODID = "woodenutilities";

    public static class Items {
        public static final String WOODEN_PLATE = "wooden_plate";
        public static final String WOODEN_BUCKET = "wooden_bucket";
        public static final String RESIN_BUCKET = "resin_bucket";
    }

    public static class Blocks {
        public static final String WOODEN_TANK = "wooden_tank";
        public static final String RESIN_EXTRACTOR = "resin_extractor";
        public static final String RESIN = "resin";
        public static final String RESIN_FLOWING = "resin_flowing";
    }
    
    public static class Resin {
    	public static final ImmutableList<ResinProvider> RESIN_PROVIDERS = ResinProviderManager.getProviders();
    }

    public static class ModIds {
        public static final String WOODEN_UTILITIES = MODID;
    }
}
