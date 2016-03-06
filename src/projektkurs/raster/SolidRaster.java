package projektkurs.raster;

import projektkurs.entity.Entity;
import projektkurs.render.Sprite;
import projektkurs.util.Direction;
import projektkurs.world.Spielfeld;

/**
 * Ein solides Raster.
 */
public class SolidRaster extends SimpleRaster {

    /**
     * Konstrukor.
     * @param id
     * ID
     * @param name
     * Name
     * @param sprite
     * Sprite
     */
    public SolidRaster(int id, String name, Sprite sprite) {
        super(id, name, sprite);
    }

    @Override
    public boolean canWalkOnFromDirection(Spielfeld map, int x, int y, Entity entity, Direction dir) {
        return false;
    }

}
