package projektkurs.raster;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationFire;
import projektkurs.raster.extra.IHasExtraInformation;
import projektkurs.render.AnimationFrame;

/**
 * Feuer.
 */
public class FireRaster extends AnimatedRaster implements IHasExtraInformation {

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     */
    public FireRaster(int id) {
        super(id, "fire", AnimationFrame.getSynchronousAnimation(Images.fire, 5));
    }

    @Override
    public ExtraInformation createExtraInformation() {
        return new ExtraInformationFire();
    }

    @Override
    public void render(Graphics2D g, int x, int y) {
        ExtraInformation extra = Main.getLevel().getMap().getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationFire) {
            AbstractRaster r = ((ExtraInformationFire) extra).getBackground();
            if (r != null) {
                r.render(g, x, y);
            }
        }
        super.render(g, x, y);
    }

    @Override
    public void renderCutScene(Graphics2D g, int x, int y) {
        ExtraInformation extra = Main.getLevel().getMap().getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationFire) {
            AbstractRaster r = ((ExtraInformationFire) extra).getBackground();
            if (r != null) {
                r.renderCutScene(g, x, y);
            }
        }
        super.renderCutScene(g, x, y);
    }
}
