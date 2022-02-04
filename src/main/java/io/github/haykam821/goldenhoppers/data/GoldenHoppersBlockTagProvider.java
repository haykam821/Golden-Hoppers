package io.github.haykam821.goldenhoppers.data;

import io.github.haykam821.goldenhoppers.Main;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.BlockTags;

public class GoldenHoppersBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public GoldenHoppersBlockTagProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void generateTags() {
		this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(Main.GOLDEN_HOPPER);
	}
}
