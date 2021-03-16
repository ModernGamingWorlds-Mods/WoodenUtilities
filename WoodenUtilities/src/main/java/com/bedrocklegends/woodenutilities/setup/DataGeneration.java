package com.bedrocklegends.woodenutilities.setup;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.datagen.WoodenBlockStateGenerator;
import com.bedrocklegends.woodenutilities.datagen.WoodenItemModelGenerator;
import com.bedrocklegends.woodenutilities.datagen.WoodenLanguageGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = WoodenUtilities.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {
    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event) {
        final DataGenerator gen = event.getGenerator();
        final ExistingFileHelper efh = event.getExistingFileHelper();

        if (event.includeClient()) {
            gen.addProvider(new WoodenItemModelGenerator(gen, efh));
            gen.addProvider(new WoodenBlockStateGenerator(gen, efh));
            gen.addProvider(new WoodenLanguageGenerator(gen, "en_us"));
        }
    }
}
