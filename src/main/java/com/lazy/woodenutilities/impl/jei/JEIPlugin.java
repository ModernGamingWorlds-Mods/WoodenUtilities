package com.lazy.woodenutilities.impl.jei;

import com.lazy.woodenutilities.WoodenUtilities;
import com.lazy.woodenutilities.client.screen.WoodCutterScreen;
import com.lazy.woodenutilities.impl.jei.category.WoodCuttingRecipeCategory;
import com.lazy.woodenutilities.util.WoodCutterRecipe;
import com.lazy.woodenutilities.util.WoodCutterUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private IRecipeCategory<WoodCutterRecipe> woodcutterCategory;

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(WoodenUtilities.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        woodcutterCategory = new WoodCuttingRecipeCategory(registration.getJeiHelpers().getGuiHelper());
        registration.addRecipeCategories(woodcutterCategory);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(WoodCutterScreen.class, 162, 5, 9, 9, woodcutterCategory.getUid());
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(WoodCutterUtils.createRecipesForJEI(), woodcutterCategory.getUid());
    }
}
