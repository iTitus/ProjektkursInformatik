package projektkurs.raster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import projektkurs.util.RenderUtil;
import projektkurs.world.Spielfeld;

/**
 * Ein einfaches Raster.
 */
public class SimpleRaster extends AbstractRaster {

    /**
     * Das Bild.
     */
    private final BufferedImage image;

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     * @param name
     *            Name
     * @param image
     *            Bild
     */
    public SimpleRaster(int id, String name, BufferedImage image) {
        super(id, name);
        this.image = image;
    }

    @Override
    public void render(Graphics2D g, Spielfeld map, int x, int y) {
        RenderUtil.drawDefaultRaster(g, image, x, y);
    }

    @Override
    public void renderCutScene(Graphics2D g, int x, int y) {
        RenderUtil.drawCutSceneRaster(g, image, x, y);
    }
}
