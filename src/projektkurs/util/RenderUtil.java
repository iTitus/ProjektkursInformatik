package projektkurs.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.entity.Entity;
import projektkurs.lib.Integers;

/**
 * Renderhilfen
 *
 */
public final class RenderUtil {

	public static void drawCutSceneRaster(Graphics2D g, BufferedImage image,
			int x, int y) {
		drawImage(g, image, (x - CutSceneManager
				.getCurrentCutSceneRenderHelper().getSightX())
				* Integers.RASTER_SIZE + Integers.WINDOW_HUD_X,
				(y - CutSceneManager.getCurrentCutSceneRenderHelper()
						.getSightY())
						* Integers.RASTER_SIZE
						+ Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE,
				Integers.RASTER_SIZE);
	}

	public static void drawDefaultEntity(Graphics2D g, Entity e) {
		drawImage(g, e.getImage(), e.getRenderX(), e.getRenderY(), e.getSizeX()
				* Integers.RASTER_SIZE, e.getSizeY() * Integers.RASTER_SIZE);
	}

	public static void drawDefaultRaster(Graphics2D g, BufferedImage img,
			int x, int y) {
		drawImage(g, img, (x - Main.getRenderHelper().getSightX())
				* Integers.RASTER_SIZE + Integers.WINDOW_HUD_X, (y - Main
				.getRenderHelper().getSightY())
				* Integers.RASTER_SIZE
				+ Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE,
				Integers.RASTER_SIZE);
	}

	public static void drawImage(Graphics2D g, BufferedImage img, int x, int y) {
		drawImage(g, img, x, y, img.getWidth(), img.getHeight());
	}

	public static void drawImage(Graphics2D g, BufferedImage img, int x, int y,
			int width, int height) {
		g.drawImage(img, x, y, width, height, null);
	}

	private RenderUtil() {
	}
}
