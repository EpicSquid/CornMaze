package dev.epicsquid.cornmaze.block

import dev.epicsquid.cornmaze.registry.BlockRegistry
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition.Builder
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.common.ForgeHooks
import net.minecraftforge.common.IPlantable
import net.minecraftforge.common.PlantType

class CornBaseBlock(props: Properties) : Block(props), IPlantable, BonemealableBlock {
	companion object {
		val AGE = BlockStateProperties.AGE_7
		private val SHAPE = box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
	}

	init {
		registerDefaultState(stateDefinition.any().setValue(AGE, 0))
	}

	override fun getShape(
		pState: BlockState,
		pLevel: BlockGetter,
		pPos: BlockPos,
		pContext: CollisionContext
	): VoxelShape = SHAPE

	override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
		val age = state.getValue(AGE)

		if (age < 7 && random.nextInt(3) == 0) {
			if (ForgeHooks.onCropsGrowPre(level, pos, state, true)) {
				level.setBlockAndUpdate(pos, state.setValue(AGE, age + 1))
				if (age + 1 >= 3) {
					val stateAbove = level.getBlockState(pos.above())
					if (stateAbove.isAir) {
						level.setBlockAndUpdate(pos.above(), BlockRegistry.cornMiddle.defaultBlockState())
					} else if (stateAbove.block == BlockRegistry.cornMiddle && stateAbove.getValue(CornMiddleBlock.AGE) < 4) {
						level.setBlockAndUpdate(
							pos.above(), stateAbove.setValue(CornMiddleBlock.AGE, age + 1 - 3)
						)
					}
				}

				if (age + 1 >= 5) {
					val stateAboveAbove = level.getBlockState(pos.above().above())
					if (stateAboveAbove.isAir) {
						level.setBlockAndUpdate(pos.above().above(), BlockRegistry.cornTop.defaultBlockState())
					} else if (stateAboveAbove.block == BlockRegistry.cornTop && stateAboveAbove.getValue(CornTopBlock.AGE) < 2) {
						level.setBlockAndUpdate(
							pos.above().above(),
							stateAboveAbove.setValue(CornTopBlock.AGE, age + 1 - 5)
						)
					}
				}
				ForgeHooks.onCropsGrowPost(level, pos, state)
			}
		}
	}

	override fun createBlockStateDefinition(builder: Builder<Block, BlockState>) {
		super.createBlockStateDefinition(builder)
		builder.add(AGE)
	}

	override fun getPlantType(level: BlockGetter?, pos: BlockPos?): PlantType = PlantType.PLAINS

	override fun getPlant(level: BlockGetter?, pos: BlockPos?): BlockState = defaultBlockState()

	override fun isValidBonemealTarget(
		level: LevelReader,
		pos: BlockPos,
		state: BlockState,
		isClient: Boolean
	): Boolean = state.getValue(AGE) < 7

	override fun isBonemealSuccess(level: Level, random: RandomSource, pos: BlockPos, state: BlockState): Boolean = true

	override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
		randomTick(state, level, pos, random)
	}
}