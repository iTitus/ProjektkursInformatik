package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.entity.Entity;
import projektkurs.util.Direction;

/**
 * Solides Raster
 */
public class SolidRaster extends SimpleRaster {

    public SolidRaster(BufferedImage image) {
        super(image);
    }

    @Override
    public boolean canWalkOnFromDirection(int x, int y, Entity entity, Direction dir) {
        return false;
    }

}
