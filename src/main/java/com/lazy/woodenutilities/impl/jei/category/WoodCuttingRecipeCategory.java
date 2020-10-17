package com.lazy.woodenutilities.impl.jei.category;

import com.lazy.woodenutilities.WoodenUtilities;
import com.lazy.woodenutilities.content.ModBlocks;
import com.lazy.woodenutilities.util.WoodCutterRecipe;
import com.sun.java.accessibility.util.Translator;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class WoodCuttingRecipeCategory implements IRecipeCategory<WoodCutterRecipe> {
    private static final int inputSlot = 0;
    private static final int outputSlot = 1;

    public static final int width = 82;
    public static final int height = 34;

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;

    public WoodCuttingRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(ModIds.JEI_ID, "textures/gui/gui_vanilla.png");
        background = guiHelper.createDrawable(location, 0, 220, width, height);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.WOOD_CUTTER.get()));
        localizedName = new TranslationTextComponent("jei.woodcutter.title").getString();
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(WoodenUtilities.MOD_ID, "wood_cutter_recipe_category");
    }

    @Override
    public Class<? extends WoodCutterRecipe> getRecipeClass() {
        return WoodCutterRecipe.class;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(WoodCutterRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, WoodCutterRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(inputSlot, true, 0, 8);
        guiItemStacks.init(outputSlot, false, 60, 8);

        guiItemStacks.set(ingredients);
    }
}