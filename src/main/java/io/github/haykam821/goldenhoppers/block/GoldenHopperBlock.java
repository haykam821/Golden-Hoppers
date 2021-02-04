package io.github.haykam821.goldenhoppers.block;

import io.github.haykam821.goldenhoppers.block.entity.GoldenHopperBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
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
	public BlockEntity createBlockEntity(BlockView world) {
		return new GoldenHopperBlockEntity();
	}
}
