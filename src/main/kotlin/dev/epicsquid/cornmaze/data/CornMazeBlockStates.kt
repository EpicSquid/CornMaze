package dev.epicsquid.cornmaze.data

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.block.CornBaseBlock
import dev.epicsquid.cornmaze.block.CornMiddleBlock
import dev.epicsquid.cornmaze.block.CornTopBlock
import dev.epicsquid.cornmaze.registry.BlockRegistry
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.client.model.generators.ConfiguredModel
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.ForgeRegistries

class CornMazeBlockStates(
	output: PackOutput,
	exFileHelper: ExistingFileHelper
) : BlockStateProvider(output, CornMaze.MODID, exFileHelper) {

	override fun registerStatesAndModels() {
		simpleBlock(
			BlockRegistry.greenGourd,
			models().cubeColumn(
				"green_gourd",
				modLoc("block/green_gourd_side"),
				modLoc("block/green_gourd_top"),
			)
		)

		simpleBlock(
			BlockRegistry.yellowGourd,
			models().cubeColumn(
				"yellow_gourd",
				modLoc("block/yellow_gourd_side"),
				modLoc("block/yellow_gourd_top"),
			)
		)

		simpleBlock(
			BlockRegistry.whiteGourd,
			models().cubeColumn(
				"white_gourd",
				modLoc("block/white_gourd_side"),
				modLoc("block/white_gourd_top"),
			)
		)

		stageBlock(BlockRegistry.gourdStem, BlockStateProperties.AGE_7) { name, stage ->
			models().singleTexture(name, mcLoc("$BLOCK_FOLDER/stem_growth$stage"), "stem", mcLoc("block/pumpkin_stem"))
				.renderType("cutout")
		}

		stageBlock(BlockRegistry.cornBase, CornBaseBlock.AGE) { name, stage ->
			models().singleTexture(name, mcLoc("$BLOCK_FOLDER/cross"), "cross", modLoc("$BLOCK_FOLDER/corn_base$stage"))
				.renderType("cutout")
		}

		stageBlock(BlockRegistry.cornMiddle, CornMiddleBlock.AGE) { name, stage ->
			models().singleTexture(name, mcLoc("$BLOCK_FOLDER/cross"), "cross", modLoc("$BLOCK_FOLDER/corn_middle$stage"))
				.renderType("cutout")
		}

		stageBlock(BlockRegistry.cornTop, CornTopBlock.AGE) { name, stage ->
			models().singleTexture(name, mcLoc("$BLOCK_FOLDER/cross"), "cross", modLoc("$BLOCK_FOLDER/corn_top$stage"))
				.renderType("cutout")
		}

		horizontalBlock(
			BlockRegistry.attachedGourdStem, models()
				.withExistingParent(BlockRegistry.attachedGourdStem.resourceLocation.path, mcLoc("$BLOCK_FOLDER/stem_fruit"))
				.texture("stem", mcLoc("block/pumpkin_stem"))
				.texture("upperStem", mcLoc("block/attached_pumpkin_stem"))
				.renderType("cutout")
		)
	}
}

fun BlockStateProvider.stageBlock(block: Block, stageProperty: IntegerProperty, model: (String, Int) -> ModelFile) {
	getVariantBuilder(block).forAllStates {
		val stage = it.getValue(stageProperty)
		val stageName = "${block.resourceLocation.path}_stage$stage"
		ConfiguredModel.builder().modelFile(model(stageName, stage)).build()
	}
}

val Block.resourceLocation: ResourceLocation
	get() = ForgeRegistries.BLOCKS.getKey(this) ?: throw IllegalStateException("Block $this is not registered")