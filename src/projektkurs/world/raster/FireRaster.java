package projektkurs.world.raster;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.render.AnimationFrame;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationFire;
import projektkurs.world.raster.extra.IHasExtraInformation;

public class FireRaster extends AnimatedRaster implements IHasExtraInformation {

    public FireRaster() {
        super(AnimationFrame.getSynchronousAnimation(Images.fire, 5));
    }

    @Override
    public ExtraInformation createExtraInformation() {
        return new ExtraInformationFire();
    }

    @Override
    public void render(Graphics2D g, int x, int y) {
        ExtraInformation extra = Main.getLevel().getCurrMap().getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationFire) {
            ((ExtraInformationFire) extra).getBackground().render(g, x, y);
        }
        super.render(g, x, y);
    }

    @Override
    public void renderCutScene(Graphics2D g, int x, int y) {
        ExtraInformation extra = Main.getLevel().getCurrMap().getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationFire) {
            ((ExtraInformationFire) extra).getBackground().renderCutScene(g, x, y);
        }
        super.renderCutScene(g, x, y);
    }
}
