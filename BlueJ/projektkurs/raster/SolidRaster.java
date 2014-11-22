package projektkurs.raster;

import java.awt.image.BufferedImage;

import projektkurs.entity.Entity;
import projektkurs.util.Direction;

/**
 * Ein solides Raster.
 */
public class SolidRaster extends SimpleRaster {

    /**
     * Konstrukor.
     *
     * @param image
     *            Bild.
     */
    public SolidRaster(BufferedImage image) {
        super(image);
    }

    @Override
    public boolean canWalkOnFromDirection(int x, int y, Entity entity, Direction dir) {
        return false;
    }

}
