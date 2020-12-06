package com.bedrocklegends.woodenutilities.data;

import com.bedrocklegends.woodenutilities.WoodenUtilities;
import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.Map;
import java.util.Objects;

public class WoodenItemModelProvider extends ItemModelProvider {
    public WoodenItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, WoodenUtilities.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (Map.Entry<String, RegistryObject<Item>> entry : WoodenItems.BUCKETS.entrySet()) {
            String s = Objects.requireNonNull(entry.getValue().get().getRegistryName()).toString();
            this.getBuilder(s).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture("layer0", "items/wooden_bucket");
        }
        this.getBuilder(Objects.requireNonNull(WoodenItems.WOODEN_BUCKET.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture("layer0", "items/wooden_bucket");
        this.getBuilder(Objects.requireNonNull(WoodenItems.WOODEN_PLATE.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", "items/wooden_plate");
        this.getBuilder(Objects.requireNonNull(WoodenItems.LUMBERJACK.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", "items/wooden_ring");
        this.getBuilder(Objects.requireNonNull(WoodenItems.NIGHT_VISION.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", "items/wooden_ring");
        this.getBuilder(Objects.requireNonNull(WoodenItems.HASTE.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", "items/wooden_ring");
        this.getBuilder(Objects.requireNonNull(WoodenItems.MIDAS_TOUCH.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", "items/wooden_ring");
        this.getBuilder(Objects.requireNonNull(WoodenItems.FLIGHT.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", "items/wooden_ring");
        this.getBuilder(Objects.requireNonNull(WoodenItems.MAGNET.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", "items/wooden_ring");
        this.getBuilder(Objects.requireNonNull(WoodenItems.REPAIR.get().getRegistryName()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", "items/wooden_ring");
    }
}
