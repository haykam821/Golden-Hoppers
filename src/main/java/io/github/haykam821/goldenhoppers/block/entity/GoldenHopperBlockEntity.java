package io.github.haykam821.goldenhoppers.block.entity;

import java.util.stream.IntStream;

import io.github.haykam821.goldenhoppers.Main;
import io.github.haykam821.goldenhoppers.screen.GoldenHopperScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.Direction;

public class GoldenHopperBlockEntity extends HopperBlockEntity implements SidedInventory {
	private static final int[] AVAILABLE_SLOTS = IntStream.range(0, 5).toArray();

	private Inventory filterInventory = new SimpleInventory(1);

	private ItemStack getFilter() {
		return this.filterInventory.getStack(0);
	}

	public void scatterFilter() {
		ItemScatterer.spawn(this.getWorld(), this.getPos(), this.filterInventory);
	}

	public boolean isAcceptedByFilter(ItemStack stack) {
		if (stack == null || stack.isEmpty()) return true;

		// No filter allows any item
		ItemStack filter = this.getFilter();
		if (filter == null || filter.isEmpty()) return true;

		return filter.getItem() == stack.getItem();
	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return AVAILABLE_SLOTS;
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, Direction direction) {
		return this.isAcceptedByFilter(stack);
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	protected Text getContainerName() {
		return new TranslatableText("container.golden_hopper");
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);
		this.filterInventory.setStack(0, ItemStack.fromTag(tag.getCompound("Filter")));
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		tag.put("Filter", this.filterInventory.getStack(0).toTag(new CompoundTag()));
		return super.toTag(tag);
	}

	@Override
	public BlockEntityType<?> getType() {
		return Main.GOLDEN_HOPPER_BLOCK_ENTITY_TYPE;
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new GoldenHopperScreenHandler(syncId, playerInventory, this, this.filterInventory);
	}
}
