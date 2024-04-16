package dev.epicsquid.cornmaze.data

import dev.epicsquid.cornmaze.CornMaze
import dev.epicsquid.cornmaze.registry.BlockRegistry
import net.minecraft.data.loot.packs.VanillaBlockLoot
import net.minecraft.world.level.block.Block
import net.minecraftforge.registries.ForgeRegistries

class CornMazeLootTables : VanillaBlockLoot() {

	override fun generate() {
		dropSelf(BlockRegistry.greenGourd)
		dropSelf(BlockRegistry.yellowGourd)
		dropSelf(BlockRegistry.whiteGourd)
	}

	override fun getKnownBlocks(): MutableIterable<Block> =
		ForgeRegistries.BLOCKS.entries
			.filter { it.key.location().namespace == CornMaze.MODID }
			.filterNot { it.value == BlockRegistry.gourdStem || it.value == BlockRegistry.attachedGourdStem }
			.map { it.value }
			.toMutableList()
}