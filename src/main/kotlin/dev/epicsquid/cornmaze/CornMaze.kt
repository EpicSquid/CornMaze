package dev.epicsquid.cornmaze

import dev.epicsquid.cornmaze.data.*
import dev.epicsquid.cornmaze.registry.ItemRegistry
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(CornMaze.MODID)
@Mod.EventBusSubscriber(modid = CornMaze.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
object CornMaze {
	const val MODID = "cornmaze"

	init {
		val modEventBus = MOD_BUS

		ItemRegistry.REGISTRY.register(modEventBus)
	}

	@SubscribeEvent
	fun gatherData(event: GatherDataEvent) {
		val generator = event.generator
		val packOutput = event.generator.packOutput
		val lookupProvider = event.lookupProvider
		val existingFileHelper = event.existingFileHelper

//		generator.addProvider(event.includeClient(), CornMazeBlockStates(packOutput, existingFileHelper))
		generator.addProvider(event.includeClient(), CornMazeItemModels(packOutput, existingFileHelper))
		generator.addProvider(event.includeClient(), CornMazeLang(packOutput, "en_us"))
//
		val blockTagsProvider = CornMazeBlockTags(packOutput, lookupProvider, existingFileHelper)
		generator.addProvider(event.includeServer(), blockTagsProvider)
		generator.addProvider(
			event.includeServer(),
			CornMazeItemTags(packOutput, lookupProvider, blockTagsProvider, existingFileHelper)
		)
		generator.addProvider(event.includeServer(), CornMazeRecipes(packOutput))
//		generator.addProvider(
//			event.includeServer(),
//			LootTableProvider(
//				packOutput,
//				emptySet(),
//				listOf(LootTableProvider.SubProviderEntry(::CornMazeLootTables, LootContextParamSets.BLOCK))
//			)
//		)
	}
}