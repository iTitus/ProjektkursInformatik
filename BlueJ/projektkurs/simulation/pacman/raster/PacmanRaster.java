package projektkurs.simulation.pacman.raster;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.entity.PacmanEntity;

public abstract class PacmanRaster {

    public static final PacmanRaster emptySpace = new EmptySpace();
    public static final PacmanRaster obstacle = new Obstacle();

    private boolean isSolid = false;

    public boolean isSolid() {
        return isSolid;
    }

    public void onWalkOn(PacmanBoard board, int x, int y, PacmanEntity e) {
        // NO-OP
    }

    public abstract void render(PacmanBoard board, int x, int y, Screen screen, int offsetX, int offsetY);

    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

}
