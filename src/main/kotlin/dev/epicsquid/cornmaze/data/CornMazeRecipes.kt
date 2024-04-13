package dev.epicsquid.cornmaze.data

import dev.epicsquid.cornmaze.registry.ItemRegistry
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.world.item.crafting.Ingredient
import java.util.function.Consumer

class CornMazeRecipes(output: PackOutput) : RecipeProvider(output) {

	override fun buildRecipes(writer: Consumer<FinishedRecipe>) {
		SimpleCookingRecipeBuilder.smoking(
			Ingredient.of(ItemRegistry.corn),
			RecipeCategory.FOOD,
			ItemRegistry.roastedCorn,
			0.35f,
			200
		)
			.unlockedBy("has_corn", has(ItemRegistry.corn))
			.save(writer)
	}
}