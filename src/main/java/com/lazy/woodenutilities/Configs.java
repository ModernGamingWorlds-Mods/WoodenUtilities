package com.lazy.woodenutilities;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class Configs {

    private static ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue NEED_AXE;
    public static ForgeConfigSpec.IntValue MINIMUM_AXE_TIER;

    public static ForgeConfigSpec.BooleanValue DROP_SHEARS_PRODUCTS;

    public static ForgeConfigSpec.IntValue SOLAR_PANEL_WORK_TIME;
    public static ForgeConfigSpec.IntValue SOLAR_PANEL_MAX_CAPACITY;
    public static ForgeConfigSpec.IntValue SOLAR_PANEL_OUTPUT;

    public static ForgeConfigSpec.DoubleValue TNT_POWER;

    public static ForgeConfigSpec.DoubleValue RING_LUMBERJACK_SPEED;

    public static ForgeConfigSpec.BooleanValue SHOW_BAUBLES_MSG;

    static {
        COMMON_BUILDER.comment("MOD: WoodenUtilities\n Configuration File");

        SHOW_BAUBLES_MSG = COMMON_BUILDER.comment("Show startup Baubles message.").define("baubles_msg", true);

        COMMON_BUILDER.push("Woodcutter Configuration");
        NEED_AXE = COMMON_BUILDER.comment("When enabled the woodcutter requires some sort of axe to work.").define("need_axe", true);
        MINIMUM_AXE_TIER = COMMON_BUILDER.comment("When enabled the woodcutter requires a minimum axe tier to work. (0 to 3)").defineInRange("minimum_axe_tier", 0, 0, 3);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Wooden Shears Configuration");
        DROP_SHEARS_PRODUCTS = COMMON_BUILDER.comment("When enabled the Wooden Shears will behave the same as the iron shears.").define("drop_shears_products", false);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Wooden Solar Panel Configuration");
        SOLAR_PANEL_WORK_TIME = COMMON_BUILDER.comment("Time that the solar panel takes to recharge.").defineInRange("work_time", 20, 1, Integer.MAX_VALUE);
        SOLAR_PANEL_MAX_CAPACITY = COMMON_BUILDER.comment("Internal solar panel capacity.").defineInRange("max_capacity", 5000, 1, Integer.MAX_VALUE);
        SOLAR_PANEL_OUTPUT = COMMON_BUILDER.comment("Solar panel energy output.").defineInRange("solar_panel_output", 10, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Wooden Tnt Configuration");
        TNT_POWER = COMMON_BUILDER.comment("Explosion damage.").defineInRange("tnt_power", 1.0D, 1.0D, Double.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push("Wooden Rings Configuration (Don't change if the current modpack doesn't contain Baubles)");
        RING_LUMBERJACK_SPEED = COMMON_BUILDER.comment("Lumberjack speed modifier.").defineInRange("ring_lumberjack_speed", 10.0D, 1.0D, Double.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public static void load(ForgeConfigSpec spec, Path path) {
        CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }

}
