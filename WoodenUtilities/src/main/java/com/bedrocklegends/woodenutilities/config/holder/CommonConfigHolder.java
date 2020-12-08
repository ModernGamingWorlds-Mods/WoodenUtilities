package com.bedrocklegends.woodenutilities.config.holder;

import com.bedrocklegends.woodenutilities.config.WoodenConfig;

public class CommonConfigHolder {

    public static boolean includeWoodenBucket;
    public static boolean includeWoodenTank;

    public static void init() {
        includeWoodenBucket = WoodenConfig.COMMON_CONFIG.includeWoodenBucket.get();
        includeWoodenTank = WoodenConfig.COMMON_CONFIG.includeWoodenTank.get();
    }
}
