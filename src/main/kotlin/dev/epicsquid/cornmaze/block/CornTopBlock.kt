package dev.epicsquid.cornmaze.block

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition.Builder
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class CornTopBlock(props: Properties) : Block(props) {
	companion object {
		val AGE = BlockStateProperties.AGE_2
		private val SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
	}

	init {
		registerDefaultState(stateDefinition.any().setValue(CornBaseBlock.AGE, 0))
	}

	override fun getShape(
		pState: BlockState,
		pLevel: BlockGetter,
		pPos: BlockPos,
		pContext: CollisionContext
	): VoxelShape = SHAPE

	override fun createBlockStateDefinition(builder: Builder<Block, BlockState>) {
		builder.add(AGE)
	}
}