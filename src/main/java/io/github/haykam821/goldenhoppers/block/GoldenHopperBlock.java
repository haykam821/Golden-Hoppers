package io.github.haykam821.goldenhoppers.block;

import io.github.haykam821.goldenhoppers.block.entity.GoldenHopperBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class GoldenHopperBlock extends HopperBlock {
	public GoldenHopperBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new GoldenHopperBlockEntity();
	}
}
