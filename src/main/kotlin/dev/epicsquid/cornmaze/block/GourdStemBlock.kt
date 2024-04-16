package dev.epicsquid.cornmaze.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction.Plane.HORIZONTAL
import net.minecraft.core.Direction.UP
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.item.Item
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.StemBlock
import net.minecraft.world.level.block.StemGrownBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.ForgeHooks

class GourdStemBlock(
	private val fruits: List<StemGrownBlock>,
	seedSupplier: () -> Item,
	properties: Properties
) : StemBlock(fruits.first(), seedSupplier, properties) {

	override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
		if (!level.isAreaLoaded(pos, 1)) return  // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (level.getRawBrightness(pos, 0) >= 9) {
			val chance = 10
			if (ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt((25.0f / chance).toInt() + 1) == 0)) {
				val age = state.getValue(AGE)
				if (age < MAX_AGE) {
					level.setBlock(pos, state.setValue(AGE, age + 1), 2)
				} else {
					val fruit = fruits[random.nextInt(fruits.size)]
					val direction = HORIZONTAL.getRandomDirection(random)
					val neighborPos = pos.relative(direction)
					val neighborGroundState = level.getBlockState(neighborPos.below())
					if (level.isEmptyBlock(neighborPos) && (neighborGroundState.canSustainPlant(level, neighborPos.below(), UP, fruit)
								|| neighborGroundState.`is`(Blocks.FARMLAND)
								|| neighborGroundState.`is`(BlockTags.DIRT))
					) {
						level.setBlockAndUpdate(neighborPos, fruit.defaultBlockState())
						level.setBlockAndUpdate(
							pos,
							fruit.attachedStem.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction)
						)
					}
				}
				ForgeHooks.onCropsGrowPost(level, pos, state)
			}
		}
	}
}