package dev.epicsquid.cornmaze.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction.Plane.HORIZONTAL
import net.minecraft.core.Direction.UP
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.ForgeHooks
import net.minecraftforge.common.IPlantable

class GourdStemBlock(
	private val fruits: List<StemGrownBlock>,
	seedSupplier: () -> Item,
	properties: Properties
) : StemBlock(fruits.first(), seedSupplier, properties) {


	override fun randomTick(pState: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
		if (!level.isAreaLoaded(pos, 1)) return  // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (level.getRawBrightness(pos, 0) >= 9) {
			val f = growthSpeed(this, level, pos)
			if (ForgeHooks.onCropsGrowPre(level, pos, pState, random.nextInt((25.0f / f).toInt() + 1) == 0)) {
				val i = pState.getValue(AGE)
				if (i < 7) {
					level.setBlock(pos, pState.setValue(AGE, i + 1), 2)
				} else {
					val fruit = fruits[random.nextInt(fruits.size)]
					val direction = HORIZONTAL.getRandomDirection(random)
					val blockpos = pos.relative(direction)
					val blockstate = level.getBlockState(blockpos.below())
					if (level.isEmptyBlock(blockpos) && (blockstate.canSustainPlant(level, blockpos.below(), UP, fruit)
								|| blockstate.`is`(Blocks.FARMLAND)
								|| blockstate.`is`(BlockTags.DIRT))
					) {
						level.setBlockAndUpdate(blockpos, fruit.defaultBlockState())
						level.setBlockAndUpdate(
							pos,
							fruit.attachedStem.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction)
						)
					}
				}
				ForgeHooks.onCropsGrowPost(level, pos, pState)
			}
		}
	}

	private fun growthSpeed(block: Block, level: Level, pos: BlockPos): Float {
		var f = 1.0f
		val blockpos: BlockPos = pos.below()

		for (i in -1..1) {
			for (j in -1..1) {
				var f1 = 0.0f
				val blockstate: BlockState = level.getBlockState(blockpos.offset(i, 0, j))
				if (blockstate.canSustainPlant(level, blockpos.offset(i, 0, j), UP, block as IPlantable?)) {
					f1 = 1.0f
					if (blockstate.isFertile(level, pos.offset(i, 0, j))) {
						f1 = 3.0f
					}
				}

				if (i != 0 || j != 0) {
					f1 /= 4.0f
				}

				f += f1
			}
		}

		val blockpos1: BlockPos = pos.north()
		val blockpos2: BlockPos = pos.south()
		val blockpos3: BlockPos = pos.west()
		val blockpos4: BlockPos = pos.east()
		val flag = level.getBlockState(blockpos3).`is`(block) || level.getBlockState(blockpos4).`is`(block)
		val flag1 = level.getBlockState(blockpos1).`is`(block) || level.getBlockState(blockpos2).`is`(block)
		if (flag && flag1) {
			f /= 2.0f
		} else {
			val flag2 = level.getBlockState(blockpos3.north()).`is`(block) || level.getBlockState(blockpos4.north())
				.`is`(block) || level.getBlockState(blockpos4.south()).`is`(block) || level.getBlockState(blockpos3.south())
				.`is`(block)
			if (flag2) {
				f /= 2.0f
			}
		}

		return f

	}
}