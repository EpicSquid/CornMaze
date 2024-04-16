package dev.epicsquid.cornmaze.registry

import dev.epicsquid.cornmaze.CornMaze
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object CreativeTabRegistry {

	val REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CornMaze.MODID)

	val tab by REGISTRY.registerObject("main") {
		CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.${CornMaze.MODID}.main"))
			.icon { ItemRegistry.corn.defaultInstance }
			.displayItems { _, output ->
				ItemRegistry.REGISTRY.entries.forEach {
					output.accept(it.get())
				}
			}
			.build()
	}
}