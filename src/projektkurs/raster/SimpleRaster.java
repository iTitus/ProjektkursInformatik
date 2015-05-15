package projektkurs.raster;

import projektkurs.render.Screen;
import projektkurs.render.Sprite;
import projektkurs.util.RenderUtil;
import projektkurs.world.Spielfeld;

/**
 * Ein einfaches Raster.
 */
public class SimpleRaster extends AbstractRaster {

    /**
     * Das Bild.
     */
    private final Sprite sprite;

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     * @param name
     *            Name
     * @param sprite
     *            Sprite
     */
    public SimpleRaster(int id, String name, Sprite sprite) {
        super(id, name);
        this.sprite = sprite;
    }

    @Override
    public void render(Screen screen, Spielfeld map, int x, int y) {
        RenderUtil.drawDefaultRaster(screen, sprite, x, y);
    }

    @Override
    public void renderCutScene(Screen screen, int x, int y) {
        RenderUtil.drawCutSceneRaster(screen, sprite, x, y);
    }
}
