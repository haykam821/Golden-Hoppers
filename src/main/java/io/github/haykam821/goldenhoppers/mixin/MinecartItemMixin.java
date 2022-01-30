package io.github.haykam821.goldenhoppers.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import io.github.haykam821.goldenhoppers.item.CustomMinecartItem;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.MinecartItem;
import net.minecraft.world.World;

@Mixin(MinecartItem.class)
public class MinecartItemMixin {
	@Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;create(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/vehicle/AbstractMinecartEntity$Type;)Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;"))
	private AbstractMinecartEntity createPlacedCustomMinecartEntity(World world, double x, double y, double z, MinecartEntity.Type type) {
		if ((Object) this instanceof CustomMinecartItem) {
			CustomMinecartItem customMinecartItem = (CustomMinecartItem) (Object) this;
			return customMinecartItem.createEntity(world, x, y, z);
		} else {
			return AbstractMinecartEntity.create(world, x, y, z, type);
		}
	}
}
