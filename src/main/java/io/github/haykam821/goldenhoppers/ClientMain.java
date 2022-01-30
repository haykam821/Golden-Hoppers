package io.github.haykam821.goldenhoppers;

import io.github.haykam821.goldenhoppers.screen.GoldenHopperScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.MinecartEntityModel;

public class ClientMain implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ScreenRegistry.register(Main.GOLDEN_HOPPER_SCREEN_HANDLER_TYPE, GoldenHopperScreen::new);

		EntityModelLayer modelLayer = new EntityModelLayer(Main.GOLDEN_HOPPER_MINECART_ID, "main");
		EntityModelLayerRegistry.registerModelLayer(modelLayer, MinecartEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(Main.GOLDEN_HOPPER_MINECART_ENTITY_TYPE, context -> {
			return new MinecartEntityRenderer<>(context, modelLayer);
		});
	}
}
