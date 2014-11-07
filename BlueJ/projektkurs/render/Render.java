package projektkurs.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.inventory.PlayerInventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;

/**
 * Renderklasse
 */
public class Render {

	/**
	 * Das Spiel-Canvas
	 */
	private final GameCanvas canvas;
	private Graphics2D g;
	private BufferStrategy strategy;

	/**
	 * Konstruktor
	 *
	 * @param gameWindow
	 *            Das Spiel-JPanel
	 */
	public Render(GameCanvas canvas) {
		this.canvas = canvas;
		strategy = null;
		g = null;
	}

	/**
	 * Gibt das aktuelle Canvas zurück
	 *
	 * @return Canvas
	 */
	public GameCanvas getGameCanvas() {
		return canvas;
	}

	public void initBuffers() {
		canvas.createBufferStrategy(2);
		strategy = canvas.getBufferStrategy();
	}

	/**
	 * Updated den Bildschirm
	 */
	public void update() {
		if (strategy != null) {
			g = (Graphics2D) strategy.getDrawGraphics();

			g.clearRect(0, 0, Integers.WINDOW_X, Integers.WINDOW_Y);
			g.setColor(Color.BLACK);

			g.drawString("FPS: " + Main.getFPS() + " - UPS: " + Main.getUPS(),
					Integers.WINDOW_HUD_X, 16);

			for (int x = 0; x < Main.getRenderHelper().getSight().length; x++) {
				for (int y = 0; y < Main.getRenderHelper().getSight()[x].length; y++) {
					drawDefaultRaster(x, y);
				}
			}

			// Iterator<Entity> i = Main.getRenderHelper().getEntitiesInSight()
			// .iterator();
			// while (i.hasNext()) {
			// drawDefaultEntity(i.next());
			// }

			for (Entity e : Main.getSpielfeld().getEntityList()) {
				if (!e.shouldDeSpawn()
						&& e.isInside(Main.getRenderHelper().getSightX(), Main
								.getRenderHelper().getSightY(),
								Integers.SIGHT_X, Integers.SIGHT_Y))
					drawDefaultEntity(e);
			}

			PlayerInventory inv = Main.getPlayer().getInventory();
			ItemStack stack = null;
			for (int i1 = 0; i1 < inv.getSize(); i1++) {
				drawImage((i1 == inv.getSelectedIndex() ? Images.slot_highlight
						: Images.slot),
						i1 * Integers.SLOT_SIZE + inv.getRelX(), inv.getRelY());
				stack = inv.getItemStackAt(i1);
				if (stack != null) {
					drawImage(stack.getImage(),
							i1 * Integers.SLOT_SIZE + inv.getRelX() + 1,
							inv.getRelY() + 1);
					g.drawString(stack.getStackSize() + "", i1
							* Integers.SLOT_SIZE + inv.getRelX() + 1,
							inv.getRelY() + 11);
				}
			}

			g.dispose();
			strategy.show();

		}
	}

	private void drawDefaultEntity(Entity e) {
		drawImage(e.getImage(), e.getRenderX(), e.getRenderY(), e.getSizeX()
				* Integers.RASTER_SIZE, e.getSizeY() * Integers.RASTER_SIZE);
	}

	private void drawDefaultRaster(int x, int y) {
		drawImage(Main.getRenderHelper().getSight()[x][y], x
				* Integers.RASTER_SIZE + Integers.WINDOW_HUD_X, y
				* Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y,
				Integers.RASTER_SIZE, Integers.RASTER_SIZE);
	}

	private void drawImage(BufferedImage img, int x, int y) {
		drawImage(img, x, y, img.getWidth(), img.getHeight());
	}

	private void drawImage(BufferedImage img, int x, int y, int width,
			int height) {
		if (g != null)
			g.drawImage(img, x, y, width, height, null);
	}

}
