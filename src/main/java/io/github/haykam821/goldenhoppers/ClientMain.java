package io.github.haykam821.goldenhoppers;

import io.github.haykam821.goldenhoppers.screen.GoldenHopperScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class ClientMain implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ScreenRegistry.register(Main.GOLDEN_HOPPER_SCREEN_HANDLER_TYPE, GoldenHopperScreen::new);
	}
}
