package projektkurs.raster;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityPlayer;
import projektkurs.lib.Images;
import projektkurs.lib.Scripts;
import projektkurs.util.Direction;

/**
 * Das Ziel.
 */
public class FinishRaster extends SimpleRaster {

    /**
     * Konstruktor.
     */
    public FinishRaster() {
        super(Images.finish);
    }

    @Override
    public void onWalkOnFromDirection(int x, int y, Entity entity, Direction d) {
        if (entity instanceof EntityPlayer) {
            Scripts.win();
        }
        super.onWalkOnFromDirection(x, y, entity, d);
    }

}
