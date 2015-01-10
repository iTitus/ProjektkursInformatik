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
     * @param id
     *            ID
     * @param name
     *            Name
     * @param image
     *            Bild.
     */
    public SolidRaster(int id, String name, BufferedImage image) {
        super(id, name, image);
    }

    @Override
    public boolean canWalkOnFromDirection(int x, int y, Entity entity, Direction dir) {
        return false;
    }

}
