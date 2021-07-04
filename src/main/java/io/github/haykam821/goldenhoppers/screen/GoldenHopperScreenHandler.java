package io.github.haykam821.goldenhoppers.screen;

import io.github.haykam821.goldenhoppers.Main;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class GoldenHopperScreenHandler extends ScreenHandler {
	private final Inventory inventory;
	private final Inventory filterInventory;
	private final Slot filterSlot;

	public GoldenHopperScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, Inventory filterInventory) {
		super(Main.GOLDEN_HOPPER_SCREEN_HANDLER_TYPE, syncId);

		// Inventory
		this.inventory = inventory;
		ScreenHandler.checkSize(this.inventory, 5);
		for (int slot = 0; slot < 5; slot++) {
			this.addSlot(new Slot(this.inventory, slot, slot * 18 + 62, 20));
		}
		this.inventory.onOpen(playerInventory.player);

		// Filter
		this.filterInventory = filterInventory;
		ScreenHandler.checkSize(this.filterInventory, 1);
		this.filterSlot = this.addSlot(new Slot(this.filterInventory, 0, 26, 20) {
			@Override
			public int getMaxItemCount() {
				return 1;
			}
		});
		this.filterInventory.onOpen(playerInventory.player);

		// Player inventory
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				this.addSlot(new Slot(playerInventory, column + row * 9 + 9, column * 18 + 8, row * 18 + 51));
			}
		}

		// Hotbar
		for (int column = 0; column < 9; column++) {
			this.addSlot(new Slot(playerInventory, column, column * 18 + 8, 109));
		}
	}

	public GoldenHopperScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, new SimpleInventory(5), new SimpleInventory(1));
	}

	protected Slot getFilterSlot() {
		return this.filterSlot;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUse(player);
	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int index) {
		Slot slot = this.slots.get(index);
		if (slot == null || !slot.hasStack()) return ItemStack.EMPTY;
	
		ItemStack slotStack = slot.getStack();
		ItemStack stack = slotStack.copy();

		if (index < this.inventory.size()) {
			if (!this.insertItem(slotStack, this.inventory.size() + 1, this.slots.size(), true)) {
				return ItemStack.EMPTY;
			}
		} else if (!this.insertItem(slotStack, 0, this.inventory.size(), false)) {
			return ItemStack.EMPTY;
		}

		if (slotStack.isEmpty()) {
			slot.setStack(ItemStack.EMPTY);
		} else {
			slot.markDirty();
		}

		return stack;
	}

	@Override
	public void close(PlayerEntity player) {
		super.close(player);

		this.inventory.onClose(player);
		this.filterInventory.onClose(player);
	}
}
