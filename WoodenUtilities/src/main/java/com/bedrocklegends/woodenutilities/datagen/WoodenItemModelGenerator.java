package com.bedrocklegends.woodenutilities.datagen;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.setup.WoodenBlocks;
import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.Map;
import java.util.Objects;

public class WoodenItemModelGenerator extends ItemModelProvider {
    public WoodenItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, WoodenUtilities.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (Map.Entry<String, RegistryObject<Item>> entry : WoodenItems.BUCKETS.entrySet()) {
            this.zeroLayered(entry.getValue(), "item/handheld", "items/wooden_bucket");
        }
        this.zeroLayered(WoodenItems.RESIN_BUCKET, "item/handheld", "items/resin_bucket");
        this.zeroLayered(WoodenItems.WOODEN_BUCKET, "item/handheld", "items/wooden_bucket");
        this.zeroLayered(WoodenItems.WOODEN_PLATE, "item/generated", "items/wooden_plate");
        this.blockItem(WoodenBlocks.WOODEN_TANK, "block/wooden_tank");
        this.blockItem(WoodenBlocks.RESIN_EXTRACTOR, "block/resin_extractor");
    }

    private void zeroLayered(RegistryObject<Item> item, String parent, String texturePath) {
        this.getBuilder(Objects.requireNonNull(item.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile(parent)).texture("layer0", texturePath);
    }

    private <T extends Block> void blockItem(RegistryObject<T> block, String texturePath) {
        this.getBuilder(Objects.requireNonNull(block.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(WoodenUtilities.ID, texturePath)));

    }
}
