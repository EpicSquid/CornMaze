package dev.epicsquid.cornmaze.registry

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.food.FoodValues
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object ItemRegistry {

	val REGISTRY = DeferredRegister.create(Registries.ITEM, CornMaze.MODID)

	val corn by REGISTRY.registerObject("corn") {
		ItemNameBlockItem(BlockRegistry.cornBase, Properties().food(FoodValues.corn))
	}

	val roastedCorn by REGISTRY.registerObject("roasted_corn") {
		Item(Properties().food(FoodValues.roasted_corn))
	}

	val gourdSeeds: Item by REGISTRY.registerObject("gourd_seeds") {
		ItemNameBlockItem(BlockRegistry.gourdStem, Properties())
	}

	val greenGourd: Item by REGISTRY.registerObject("green_gourd") {
		BlockItem(BlockRegistry.greenGourd, Properties())
	}

	val yellowGourd: Item by REGISTRY.registerObject("yellow_gourd") {
		BlockItem(BlockRegistry.yellowGourd, Properties())
	}

	val whiteGourd: Item by REGISTRY.registerObject("white_gourd") {
		BlockItem(BlockRegistry.whiteGourd, Properties())
	}
}