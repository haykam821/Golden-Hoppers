package io.github.haykam821.goldenhoppers.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class GoldenHoppersDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		dataGenerator.addProvider(GoldenHoppersModelProvider::new);
		dataGenerator.addProvider(GoldenHoppersBlockTagProvider::new);
		dataGenerator.addProvider(GoldenHoppersItemTagProvider::new);
	}
}