package com.bedrocklegends.woodenutilities.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    private static final String FEATURE_TOGGLE_CATEGORY = "feature_toggle";

    public ForgeConfigSpec.BooleanValue includeWoodenBucket;
    public ForgeConfigSpec.BooleanValue includeWoodenTank;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        initFeatureFlags(builder);
    }

    private void initFeatureFlags(ForgeConfigSpec.Builder builder) {
        builder.comment("Feature Toggle").push(FEATURE_TOGGLE_CATEGORY);

        includeWoodenBucket = builder.define("include_wooden_bucket", true);
        includeWoodenTank = builder.define("include_wooden_tank", true);

        builder.pop();
    }
}
