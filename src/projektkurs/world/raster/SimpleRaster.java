package projektkurs.world.raster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import projektkurs.util.RenderUtil;

public class SimpleRaster extends AbstractRaster {

	private final BufferedImage image;

	public SimpleRaster(BufferedImage image) {
		this.image = image;
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		RenderUtil.drawDefaultRaster(g, image, x, y);
	}

	@Override
	public void renderCutScene(Graphics2D g, int x, int y) {
		RenderUtil.drawCutSceneRaster(g, image, x, y);
	}
}
