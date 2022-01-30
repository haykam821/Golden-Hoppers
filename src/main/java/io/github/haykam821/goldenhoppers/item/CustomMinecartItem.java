package io.github.haykam821.goldenhoppers.item;

import java.util.Objects;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.MinecartItem;
import net.minecraft.world.World;

public class CustomMinecartItem extends MinecartItem {
	private final EntityType<? extends AbstractMinecartEntity> entityType;

	public CustomMinecartItem(EntityType<? extends AbstractMinecartEntity> entityType, Item.Settings settings) {
		super(AbstractMinecartEntity.Type.HOPPER, settings);

		Objects.requireNonNull(entityType, "Entity type must not be null");
		this.entityType = entityType;
	}

	public AbstractMinecartEntity createEntity(World world, double x, double y, double z) {
		AbstractMinecartEntity entity = this.entityType.create(world);

		entity.setPosition(x, y, z);
		entity.prevX = x;
		entity.prevY = y;
		entity.prevZ = z;

		return entity;
	}
}
