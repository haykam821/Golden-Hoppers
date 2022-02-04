package io.github.haykam821.goldenhoppers.data;

import io.github.haykam821.goldenhoppers.Main;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.ItemTags;

public class GoldenHoppersItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public GoldenHoppersItemTagProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void generateTags() {
		this.getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED)
			.add(Main.GOLDEN_HOPPER_ITEM)
			.add(Main.GOLDEN_HOPPER_MINECART_ITEM);
	}
}
