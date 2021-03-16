package com.bedrocklegends.woodenutilities.datagen;

import com.bedrocklegends.woodenutilities.setup.SetupWoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.SetupWoodenItems;
import com.bedrocklegends.woodenutilities.util.Constants;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class WoodenLanguageGenerator extends LanguageProvider {
    public WoodenLanguageGenerator(DataGenerator gen, String locale) {
        super(gen, Constants.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        this.add(SetupWoodenBlocks.WOODEN_TANK.get(), "Wooden Tank");
        this.add(SetupWoodenBlocks.RESIN_EXTRACTOR.get(), "Resin Extractor");
        this.add("fluid.woodenutilities.resin", "Resin");
        this.add(SetupWoodenItems.RESIN_BUCKET.get(), "Resin Bucket");
    }
}
