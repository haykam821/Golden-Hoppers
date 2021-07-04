package io.github.haykam821.goldenhoppers.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import io.github.haykam821.goldenhoppers.Main;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GoldenHopperScreen extends HandledScreen<GoldenHopperScreenHandler> {
	private static final Identifier TEXTURE = new Identifier(Main.MOD_ID, "textures/gui/container/golden_hopper.png");

	public GoldenHopperScreen(GoldenHopperScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
		this.passEvents = false;

		this.backgroundHeight = 133;
		this.playerInventoryTitleY = this.backgroundHeight - 94;
	}

	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);

		this.drawMouseoverTooltip(matrices, mouseX, mouseY);
	}

	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.setShaderTexture(0, TEXTURE);

		int x = (this.width - this.backgroundWidth) / 2;
		int y = (this.height - this.backgroundHeight) / 2;

		this.drawTexture(matrices, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);

		// Render the filter slot placeholder
		Slot filterSlot = this.handler.getFilterSlot();
		if (!filterSlot.hasStack()) {
			this.drawFilterIcon(matrices, filterSlot);
		}
	}

	private void drawFilterIcon(MatrixStack matrices, Slot slot) {
		this.drawTexture(matrices, this.x + slot.x, this.y + slot.y, this.backgroundWidth, 0, 16, 16);
	}
}