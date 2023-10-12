package io.github.haykam821.goldenhoppers.entity;

import io.github.haykam821.goldenhoppers.Main;
import io.github.haykam821.goldenhoppers.block.entity.GoldenHopper;
import io.github.haykam821.goldenhoppers.screen.GoldenHopperScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ItemScatterer;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class GoldenHopperMinecartEntity extends HopperMinecartEntity implements GoldenHopper {
	private Inventory filterInventory = new SimpleInventory(1);

	public GoldenHopperMinecartEntity(EntityType<GoldenHopperMinecartEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public ItemStack getFilter() {
		return this.filterInventory.getStack(0);
	}

	@Override
	public BlockState getDefaultContainedBlock() {
		return Main.GOLDEN_HOPPER.getDefaultState();
	}

	@Override
	public void dropItems(DamageSource damageSource) {
		super.dropItems(damageSource);

		if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
			ItemScatterer.spawn(this.getWorld(), this, this.filterInventory);
		}
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		this.filterInventory.setStack(0, ItemStack.fromNbt(nbt.getCompound(FILTER_KEY)));
	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {
		nbt.put(FILTER_KEY, this.filterInventory.getStack(0).writeNbt(new NbtCompound()));
		super.writeCustomDataToNbt(nbt);
	}

	@Override
	public ItemStack getPickBlockStack() {
		return new ItemStack(Main.GOLDEN_HOPPER_MINECART_ITEM);
	}

	public ScreenHandler getScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new GoldenHopperScreenHandler(syncId, playerInventory, this, this.filterInventory);
	}
}
