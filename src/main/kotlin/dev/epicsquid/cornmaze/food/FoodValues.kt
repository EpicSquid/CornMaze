package dev.epicsquid.cornmaze.food

import net.minecraft.world.food.FoodProperties

object FoodValues {

	val corn = FoodProperties.Builder().nutrition(2).saturationMod(0.3f).build()
	val roasted_corn = FoodProperties.Builder().nutrition(5).saturationMod(0.6f).build()
}