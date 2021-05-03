package com.bedrocklegends.woodenutilities.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    private static final String FEATURE_TOGGLE_CATEGORY = "feature_toggle";

    public ForgeConfigSpec.BooleanValue includeWoodenBucket;
    public ForgeConfigSpec.BooleanValue includeWoodenTank;

    //Pls let me know if we just change this to other place. this config system is very messy.
    public ForgeConfigSpec.IntValue maxUpgrades;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        initFeatureFlags(builder);
    }

    private void initFeatureFlags(ForgeConfigSpec.Builder builder) {
        builder.comment("Feature Toggle").push(FEATURE_TOGGLE_CATEGORY);

        includeWoodenBucket = builder.define("include_wooden_bucket", true);
        includeWoodenTank = builder.define("include_wooden_tank", true);

        builder.pop();

        builder.comment("Other").push("Other Configs");

        maxUpgrades = builder.defineInRange("max_upgrades", 5, 1, Integer.MAX_VALUE);

        builder.pop();
    }
}
