package com.bedrocklegends.woodenutilities.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);
	public static final ForgeConfigSpec spec = BUILDER.build();

	public static class General {		
	    public final ForgeConfigSpec.ConfigValue<Boolean> includeWoodenBucket;
	    public final ForgeConfigSpec.ConfigValue<Boolean> includeWoodenTank;

	    public final ForgeConfigSpec.ConfigValue<Integer> maxUpgrades;

		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			includeWoodenBucket = builder
					.comment("Description")
					.define("include_wooden_bucket", true);
			includeWoodenTank = builder
					.comment("Description")
					.define("include_wooden_tank", true);
			
			maxUpgrades = builder
					.comment("Description")
					.defineInRange("max_upgrades", 5, 1, Integer.MAX_VALUE);
			
			builder.pop();
		}
	}
}