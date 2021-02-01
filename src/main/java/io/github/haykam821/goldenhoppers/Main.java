package io.github.haykam821.goldenhoppers;

import io.github.haykam821.goldenhoppers.block.GoldenHopperBlock;
import io.github.haykam821.goldenhoppers.block.entity.GoldenHopperBlockEntity;
import io.github.haykam821.goldenhoppers.screen.GoldenHopperScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static final String MOD_ID = "goldenhoppers";

	private static final Identifier GOLDEN_HOPPER_ID = new Identifier(MOD_ID, "golden_hopper");
	public static final Block GOLDEN_HOPPER = new GoldenHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER).materialColor(MaterialColor.GOLD));
	public static final Item GOLDEN_HOPPER_ITEM = new BlockItem(GOLDEN_HOPPER, new Item.Settings().group(ItemGroup.REDSTONE));

	public static final BlockEntityType<GoldenHopperBlockEntity> GOLDEN_HOPPER_BLOCK_ENTITY_TYPE = BlockEntityType.Builder.create(GoldenHopperBlockEntity::new, GOLDEN_HOPPER).build(null);
	public static final ScreenHandlerType<GoldenHopperScreenHandler> GOLDEN_HOPPER_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(GOLDEN_HOPPER_ID, GoldenHopperScreenHandler::new);

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, GOLDEN_HOPPER_ID, GOLDEN_HOPPER);
		Registry.register(Registry.ITEM, GOLDEN_HOPPER_ID, GOLDEN_HOPPER_ITEM);

		Registry.register(Registry.BLOCK_ENTITY_TYPE, GOLDEN_HOPPER_ID, GOLDEN_HOPPER_BLOCK_ENTITY_TYPE);
	}
}