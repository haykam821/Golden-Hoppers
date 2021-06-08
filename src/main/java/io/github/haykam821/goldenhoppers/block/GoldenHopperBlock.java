package io.github.haykam821.goldenhoppers.block;

import io.github.haykam821.goldenhoppers.Main;
import io.github.haykam821.goldenhoppers.block.entity.GoldenHopperBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GoldenHopperBlock extends HopperBlock {
	public GoldenHopperBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (!state.isOf(newState.getBlock())) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof GoldenHopperBlockEntity) {
				((GoldenHopperBlockEntity) blockEntity).scatterFilter();
			}

			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new GoldenHopperBlockEntity(pos, state);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return world.isClient() ? null : checkType(type, Main.GOLDEN_HOPPER_BLOCK_ENTITY_TYPE, HopperBlockEntity::serverTick);
	}
}
