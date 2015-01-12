package projektkurs.raster;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityPlayer;
import projektkurs.lib.Images;
import projektkurs.lib.Scripts;
import projektkurs.util.Direction;
import projektkurs.world.Spielfeld;

/**
 * Das Ziel.
 */
public class FinishRaster extends SimpleRaster {

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     */
    public FinishRaster(int id) {
        super(id, "finish", Images.finish);
    }

    @Override
    public void onWalkOnFromDirection(Spielfeld map, int x, int y, Entity entity, Direction d) {
        if (entity instanceof EntityPlayer) {
            Scripts.win();
        }
        super.onWalkOnFromDirection(map, x, y, entity, d);
    }

}
