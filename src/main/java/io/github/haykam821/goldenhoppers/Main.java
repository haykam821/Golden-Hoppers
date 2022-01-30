package io.github.haykam821.goldenhoppers;

import io.github.haykam821.goldenhoppers.block.GoldenHopperBlock;
import io.github.haykam821.goldenhoppers.block.entity.GoldenHopperBlockEntity;
import io.github.haykam821.goldenhoppers.entity.GoldenHopperMinecartEntity;
import io.github.haykam821.goldenhoppers.item.CustomMinecartItem;
import io.github.haykam821.goldenhoppers.screen.GoldenHopperScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static final String MOD_ID = "goldenhoppers";

	private static final Identifier GOLDEN_HOPPER_ID = new Identifier(MOD_ID, "golden_hopper");
	public static final Block GOLDEN_HOPPER = new GoldenHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER).mapColor(MapColor.GOLD));
	public static final Item GOLDEN_HOPPER_ITEM = new BlockItem(GOLDEN_HOPPER, new Item.Settings().group(ItemGroup.REDSTONE));

	public static final BlockEntityType<GoldenHopperBlockEntity> GOLDEN_HOPPER_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder.create(GoldenHopperBlockEntity::new, GOLDEN_HOPPER).build(null);
	public static final ScreenHandlerType<GoldenHopperScreenHandler> GOLDEN_HOPPER_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(GOLDEN_HOPPER_ID, GoldenHopperScreenHandler::new);

	protected static final Identifier GOLDEN_HOPPER_MINECART_ID = new Identifier(MOD_ID, "golden_hopper_minecart");
	public static final EntityType<GoldenHopperMinecartEntity> GOLDEN_HOPPER_MINECART_ENTITY_TYPE = FabricEntityTypeBuilder.<GoldenHopperMinecartEntity>create(SpawnGroup.MISC, GoldenHopperMinecartEntity::new)
		.dimensions(EntityDimensions.changing(0.98f, 0.7f))
		.trackRangeChunks(8)
		.build();
	public static final Item GOLDEN_HOPPER_MINECART_ITEM = new CustomMinecartItem(GOLDEN_HOPPER_MINECART_ENTITY_TYPE, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION));

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, GOLDEN_HOPPER_ID, GOLDEN_HOPPER);
		Registry.register(Registry.ITEM, GOLDEN_HOPPER_ID, GOLDEN_HOPPER_ITEM);

		Registry.register(Registry.BLOCK_ENTITY_TYPE, GOLDEN_HOPPER_ID, GOLDEN_HOPPER_BLOCK_ENTITY_TYPE);

		Registry.register(Registry.ENTITY_TYPE, GOLDEN_HOPPER_MINECART_ID, GOLDEN_HOPPER_MINECART_ENTITY_TYPE);
		Registry.register(Registry.ITEM, GOLDEN_HOPPER_MINECART_ID, GOLDEN_HOPPER_MINECART_ITEM);
	}
}