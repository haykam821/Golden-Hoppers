package io.github.haykam821.goldenhoppers.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import io.github.haykam821.goldenhoppers.Main;
import io.github.haykam821.goldenhoppers.entity.GoldenHopperMinecartEntity;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.item.ItemConvertible;

@Mixin(HopperMinecartEntity.class)
public class HopperMinecartEntityMixin {
	@ModifyArg(method = "dropItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/HopperMinecartEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;", ordinal = 0))
	private ItemConvertible modifyDroppedBlock(ItemConvertible item) {
		return (Object) this instanceof GoldenHopperMinecartEntity ? Main.GOLDEN_HOPPER : item;
	}
}
