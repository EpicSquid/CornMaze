package dev.epicsquid.cornmaze.data

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.registry.BlockRegistry
import net.minecraft.core.HolderLookup.Provider
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
import net.minecraftforge.common.data.BlockTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class CornMazeBlockTags(
	output: PackOutput,
	lookupProvider: CompletableFuture<Provider>,
	existingFileHelper: ExistingFileHelper?
) : BlockTagsProvider(output, lookupProvider, CornMaze.MODID, existingFileHelper) {

	override fun addTags(provider: Provider) {
		tag(BlockTags.MINEABLE_WITH_AXE)
			.add(
				BlockRegistry.greenGourd,
				BlockRegistry.yellowGourd,
				BlockRegistry.whiteGourd
			)
		tag(BlockTags.MINEABLE_WITH_HOE)
			.add(
				BlockRegistry.gourdStem,
				BlockRegistry.attachedGourdStem
			)
	}
}