package dev.epicsquid.cornmaze.block

import dev.epicsquid.cornmaze.registry.BlockRegistry
import net.minecraft.world.level.block.AttachedStemBlock
import net.minecraft.world.level.block.StemBlock
import net.minecraft.world.level.block.StemGrownBlock

class GourdBlock(properties: Properties) : StemGrownBlock(properties) {

	override fun getStem(): StemBlock = BlockRegistry.gourdStem

	override fun getAttachedStem(): AttachedStemBlock = BlockRegistry.attachedGourdStem
}