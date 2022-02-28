package io.github.haykam821.goldenhoppers.data;

import java.util.Optional;

import io.github.haykam821.goldenhoppers.Main;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HopperBlock;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class GoldenHoppersModelProvider extends FabricModelProvider {
	public static final Model HOPPER_MODEL = GoldenHoppersModelProvider.createModel("hopper", TextureKey.PARTICLE, TextureKey.TOP, TextureKey.SIDE, TextureKey.INSIDE);
	public static final Model HOPPER_SIDE_MODEL = GoldenHoppersModelProvider.createModel("hopper_side", "_side", TextureKey.PARTICLE, TextureKey.TOP, TextureKey.SIDE, TextureKey.INSIDE);

	public GoldenHoppersModelProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator modelGenerator) {
		GoldenHoppersModelProvider.registerHopper(Main.GOLDEN_HOPPER, Blocks.GOLD_BLOCK, modelGenerator);
	}

	@Override
	public void generateItemModels(ItemModelGenerator modelGenerator) {
		modelGenerator.register(Main.GOLDEN_HOPPER_ITEM, Models.GENERATED);
		modelGenerator.register(Main.GOLDEN_HOPPER_MINECART_ITEM, Models.GENERATED);
	}

	public static void registerHopper(Block hopper, Block base, BlockStateModelGenerator modelGenerator) {
		TextureMap textures = GoldenHoppersModelProvider.createHopperTextures(base);

		Identifier modelId = HOPPER_MODEL.upload(hopper, textures, modelGenerator.modelCollector);
		Identifier sideModelId = HOPPER_SIDE_MODEL.upload(hopper, textures, modelGenerator.modelCollector);

		BlockStateVariantMap variants = BlockStateVariantMap.create(HopperBlock.FACING)
			.register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, modelId))
			.register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, sideModelId))
			.register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, sideModelId).put(VariantSettings.Y, VariantSettings.Rotation.R90))
			.register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, sideModelId).put(VariantSettings.Y, VariantSettings.Rotation.R180))
			.register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, sideModelId).put(VariantSettings.Y, VariantSettings.Rotation.R270));

		modelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(hopper).coordinate(variants));
	}

	public static TextureMap createHopperTextures(Block block) {
		Identifier id = TextureMap.getId(block);

		return new TextureMap()
			.put(TextureKey.PARTICLE, id)
			.put(TextureKey.TOP, id)
			.put(TextureKey.SIDE, id)
			.put(TextureKey.INSIDE, id);
	}

	private static Model createModel(String parent, TextureKey... requiredTextures) {
		Identifier parentId = new Identifier("block/" + parent);
		return new Model(Optional.of(parentId), Optional.empty(), requiredTextures);
	}

	private static Model createModel(String parent, String suffix, TextureKey... requiredTextures) {
		Identifier parentId = new Identifier("block/" + parent);
		return new Model(Optional.of(parentId), Optional.of(suffix), requiredTextures);
	}
}
