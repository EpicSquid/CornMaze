package dev.epicsquid.cornmaze.data

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.registry.ItemRegistry
import dev.epicsquid.squidink.data.block
import net.minecraft.data.PackOutput
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper

class CornMazeItemModels(
	output: PackOutput,
	existingFileHelper: ExistingFileHelper
) : ItemModelProvider(output, CornMaze.MODID, existingFileHelper) {

	override fun registerModels() {
		basicItem(ItemRegistry.corn)
		basicItem(ItemRegistry.roastedCorn)
		basicItem(ItemRegistry.gourdSeeds)
		block(ItemRegistry.greenGourd)
		block(ItemRegistry.yellowGourd)
		block(ItemRegistry.whiteGourd)
	}
}