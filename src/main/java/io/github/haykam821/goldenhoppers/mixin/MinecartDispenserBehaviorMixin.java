package io.github.haykam821.goldenhoppers.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import io.github.haykam821.goldenhoppers.item.CustomMinecartItem;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.world.World;

@Mixin(targets = "net.minecraft.item.MinecartItem$1")
public class MinecartDispenserBehaviorMixin {
	@Redirect(method = "dispenseSilently(Lnet/minecraft/util/math/BlockPointer;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;create(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/vehicle/AbstractMinecartEntity$Type;)Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;"))
	private AbstractMinecartEntity createDispensedCustomMinecartEntity(World world, double x, double y, double z, MinecartEntity.Type type, BlockPointer pointer, ItemStack stack) {
		Item item = stack.getItem();
		if ((Object) item instanceof CustomMinecartItem) {
			CustomMinecartItem customMinecartItem = (CustomMinecartItem) (Object) item;
			return customMinecartItem.createEntity(world, x, y, z);
		} else {
			return AbstractMinecartEntity.create(world, x, y, z, type);
		}
	}
}
