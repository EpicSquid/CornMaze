package dev.epicsquid.cornmaze.data

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.registry.ItemRegistry
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.LanguageProvider

class CornMazeLang(
	output: PackOutput,
	locale: String
) : LanguageProvider(output, CornMaze.MODID, locale) {

	override fun addTranslations() {
		add(ItemRegistry.corn, "Corn")
		add(ItemRegistry.roastedCorn, "Roasted Corn")
	}
}