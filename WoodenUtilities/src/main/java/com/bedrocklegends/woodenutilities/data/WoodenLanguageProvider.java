package com.bedrocklegends.woodenutilities.data;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.setup.WoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class WoodenLanguageProvider extends LanguageProvider {
    public WoodenLanguageProvider(DataGenerator gen, String locale) {
        super(gen, WoodenUtilities.ID, locale);
    }

    @Override
    protected void addTranslations() {
        this.add(WoodenBlocks.WOODEN_TANK.get(), "Wooden Tank");
        this.add(WoodenBlocks.RESIN_EXTRACTOR.get(), "Resin Extractor");
        this.add("fluid.woodenutilities.resin", "Resin");
        this.add(WoodenItems.RESIN_BUCKET.get(), "Resin Bucket");
    }
}
