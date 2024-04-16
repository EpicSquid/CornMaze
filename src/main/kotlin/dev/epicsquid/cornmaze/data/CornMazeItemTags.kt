package dev.epicsquid.cornmaze.data

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.registry.ItemRegistry
import dev.epicsquid.squidink.utils.forgeTag
import net.minecraft.core.HolderLookup.Provider
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraftforge.common.Tags
import net.minecraftforge.common.data.BlockTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class CornMazeItemTags(
	output: PackOutput,
	lookupProvider: CompletableFuture<Provider>,
	blockTags: BlockTagsProvider,
	existingFileHelper: ExistingFileHelper
) : ItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), CornMaze.MODID, existingFileHelper) {

	companion object {
		val CORN = "crops/corn".forgeTag
	}

	override fun addTags(pProvider: Provider) {
		tag(CORN)
			.add(ItemRegistry.corn)
	}
}
