package dev.epicsquid.cornmaze.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.Item
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.AttachedStemBlock
import net.minecraft.world.level.block.StemBlock
import net.minecraft.world.level.block.StemGrownBlock
import net.minecraft.world.level.block.state.BlockState

class GourdAttachedStemBlock(
	private val fruits: List<StemGrownBlock>,
	seedSupplier: () -> Item,
	properties: Properties
) : AttachedStemBlock(fruits.first(), seedSupplier, properties) {

	override fun updateShape(
		state: BlockState,
		facing: Direction,
		facingState: BlockState,
		level: LevelAccessor,
		currentPos: BlockPos,
		facingPos: BlockPos
	): BlockState {
		fruits.forEach { fruit ->
			if (!facingState.`is`(fruit) && facing == state.getValue(FACING))
				return fruit.stem.defaultBlockState().setValue(StemBlock.AGE, 7)
		}
		return super.updateShape(state, facing, facingState, level, currentPos, facingPos)
	}
}