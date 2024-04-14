package dev.epicsquid.cornmaze.registry

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.block.GourdAttachedStemBlock
import dev.epicsquid.cornmaze.block.GourdBlock
import dev.epicsquid.cornmaze.block.GourdStemBlock
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.PumpkinBlock
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

	val gourdStem by REGISTRY.registerObject("gourd_stem") {
		GourdStemBlock(
			listOf(greenGourd),
			{ ItemRegistry.gourdSeeds },
			gourdStemProps
		)
	}

	val gourdAttachedStem by REGISTRY.registerObject("gourd_attached_stem") {
		GourdAttachedStemBlock(
			listOf(greenGourd),
			{ ItemRegistry.gourdSeeds },
			gourdStemProps
		)
	}

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
}