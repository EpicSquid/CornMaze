package dev.epicsquid.cornmaze.registry

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.food.FoodValues
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object ItemRegistry {

	val REGISTRY = DeferredRegister.create(Registries.ITEM, CornMaze.MODID)

	val corn by REGISTRY.registerObject("corn") {
		Item(Properties().food(FoodValues.corn))
	}

	val roastedCorn by REGISTRY.registerObject("roasted_corn") {
		Item(Properties().food(FoodValues.roasted_corn))
	}
}