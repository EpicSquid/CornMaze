package dev.epicsquid.cornmaze.data

import dev.epicsquid.cornmaze.CornMaze
import net.minecraft.core.HolderLookup.Provider
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.BlockTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class CornMazeBlockTags(
	output: PackOutput,
	lookupProvider: CompletableFuture<Provider>,
	existingFileHelper: ExistingFileHelper?
) : BlockTagsProvider(output, lookupProvider, CornMaze.MODID, existingFileHelper) {

	override fun addTags(provider: Provider) {
	}
}