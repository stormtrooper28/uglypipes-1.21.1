package com.stormtrooper28.uglypipes;

import com.stormtrooper28.uglypipes.blocks.BlockInitializer;
import com.stormtrooper28.uglypipes.items.ItemInitializer;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

/**
 * Build Recipes for UglyPipes
 * Currently includes: UglyWrenchItem, UglyItemPipeBlock
 * */
public class UglyPipesRecipeProvider extends FabricRecipeProvider {
    public UglyPipesRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    public void generate(RecipeExporter recipeExporter) {
        LOGGER.info("Ruining your crafting table with such filthy recipes");

        // Ugly Wrench Mossy Cobble Recipe
        buildWrenchRecipe(recipeExporter, Items.MOSSY_COBBLESTONE, Items.MOSSY_COBBLESTONE);
        // Ugly Wrench Exposed Copper Recipe
        buildWrenchRecipe(recipeExporter, Items.EXPOSED_COPPER, Items.RAW_COPPER);
        // Ugly Wrench Weathered Copper Recipe
        buildWrenchRecipe(recipeExporter, Items.WEATHERED_COPPER, Items.RAW_COPPER);
        // Ugly Wrench Oxidized Copper Recipe
        buildWrenchRecipe(recipeExporter, Items.OXIDIZED_COPPER, Items.RAW_COPPER);

        // Ugly Pipe Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, BlockInitializer.ITEM_PIPE, 16)
                .pattern("MCM")
                .input('M', Items.MOSSY_COBBLESTONE)
                .input('C', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(recipeExporter);
    }

    /**
     * Builds recipe in both directions for Ugly Wrench
     * @param recipeExporter The thingy Minecraft uses to give the game your recipes
     * @param centerItem Item in the center of the recipe
     * @param recipeUnlockItem Item the player has to have in their inventory to unlock this recipe
     * */
    private void buildWrenchRecipe(RecipeExporter recipeExporter, Item centerItem, Item recipeUnlockItem) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ItemInitializer.UGLY_WRENCH)
                .pattern(" S ")
                .pattern(" CS")
                .pattern("S  ")
                .input('S', Items.STICK)
                .input('C', centerItem)
                .criterion(hasItem(recipeUnlockItem), conditionsFromItem(recipeUnlockItem))
                .offerTo(recipeExporter, Identifier.of(centerItem.getTranslationKey().toLowerCase() + "_cored_ugly_wrench_lean_right"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ItemInitializer.UGLY_WRENCH)
                .pattern(" S ")
                .pattern("SC ")
                .pattern("  S")
                .input('S', Items.STICK)
                .input('C', centerItem)
                .criterion(hasItem(recipeUnlockItem), conditionsFromItem(recipeUnlockItem))
                .offerTo(recipeExporter, Identifier.of(centerItem.getTranslationKey().toLowerCase() + "_cored_ugly_wrench_lean_left"));
    }
}
