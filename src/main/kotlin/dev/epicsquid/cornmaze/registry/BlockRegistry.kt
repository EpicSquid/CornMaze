package dev.epicsquid.cornmaze.registry

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.block.*
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument.DIDGERIDOO
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction.DESTROY
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object BlockRegistry {

	val REGISTRY = DeferredRegister.create(Registries.BLOCK, CornMaze.MODID)

	private val gourdStemProps = Properties.of()
		.mapColor(MapColor.PLANT)
		.noCollission()
		.randomTicks()
		.instabreak()
		.sound(SoundType.HARD_CROP)
		.pushReaction(DESTROY)

	val greenGourd by REGISTRY.registerObject("green_gourd") {
		GourdBlock(
			Properties.of()
				.mapColor(MapColor.COLOR_GREEN)
				.instrument(DIDGERIDOO)
				.strength(1.0f)
				.sound(SoundType.WOOD)
				.pushReaction(DESTROY)
		)
	}

	val yellowGourd by REGISTRY.registerObject("yellow_gourd") {
		GourdBlock(
			Properties.of()
				.mapColor(MapColor.COLOR_YELLOW)
				.instrument(DIDGERIDOO)
				.strength(1.0f)
				.sound(SoundType.WOOD)
				.pushReaction(DESTROY)
		)
	}

	val whiteGourd by REGISTRY.registerObject("white_gourd") {
		GourdBlock(
			Properties.of()
				.mapColor(MapColor.COLOR_LIGHT_GRAY)
				.instrument(DIDGERIDOO)
				.strength(1.0f)
				.sound(SoundType.WOOD)
				.pushReaction(DESTROY)
		)
	}

	val gourdStem by REGISTRY.registerObject("gourd_stem") {
		GourdStemBlock(
			listOf(greenGourd, yellowGourd, whiteGourd),
			{ ItemRegistry.gourdSeeds },
			gourdStemProps
		)
	}

	val attachedGourdStem by REGISTRY.registerObject("attached_gourd_stem") {
		AttachedGourdStemBlock(
			listOf(greenGourd, yellowGourd, whiteGourd),
			{ ItemRegistry.gourdSeeds },
			gourdStemProps
		)
	}

	val cornProps = Properties.of()
		.mapColor(MapColor.PLANT)
		.noCollission()
		.instabreak()
		.sound(SoundType.CROP)
		.pushReaction(DESTROY)

	val cornBase by REGISTRY.registerObject("corn_base") {
		CornBaseBlock(cornProps.randomTicks())
	}

	val cornMiddle by REGISTRY.registerObject("corn_middle") {
		CornMiddleBlock(cornProps)
	}

	val cornTop by REGISTRY.registerObject("corn_top") {
		CornTopBlock(cornProps)
	}
}