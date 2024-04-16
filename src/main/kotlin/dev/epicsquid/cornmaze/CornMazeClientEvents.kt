package dev.epicsquid.cornmaze

import dev.epicsquid.cornmaze.registry.BlockRegistry
import net.minecraft.client.renderer.BiomeColors
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.RegisterColorHandlersEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = CornMaze.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object CornMazeClientEvents {

	@SubscribeEvent
	fun registerBlockColors(event: RegisterColorHandlersEvent.Block) {
		event.register(
			{ state, world, pos, tintIndex ->
				if (world != null && pos != null)
					BiomeColors.getAverageGrassColor(world, pos)
				else
					-1
			}, BlockRegistry.gourdStem, BlockRegistry.attachedGourdStem
		)
	}
}