package dev.epicsquid.cornmaze.data

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.registry.BlockRegistry
import net.minecraft.data.PackOutput
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper

class CornMazeBlockStates(
	output: PackOutput,
	exFileHelper: ExistingFileHelper
) : BlockStateProvider(output, CornMaze.MODID, exFileHelper) {

	override fun registerStatesAndModels() {
		simpleBlock(
			BlockRegistry.greenGourd,
			models().cubeTop(
				"green_gourd",
				modLoc("block/green_gourd_side"),
				modLoc("block/green_gourd_top"),
			)
		)

		simpleBlock(
			BlockRegistry.yellowGourd,
			models().cubeTop(
				"yellow_gourd",
				modLoc("block/yellow_gourd_side"),
				modLoc("block/yellow_gourd_top"),
			)
		)

		simpleBlock(
			BlockRegistry.whiteGourd,
			models().cubeTop(
				"white_gourd",
				modLoc("block/white_gourd_side"),
				modLoc("block/white_gourd_top"),
			)
		)
	}
}