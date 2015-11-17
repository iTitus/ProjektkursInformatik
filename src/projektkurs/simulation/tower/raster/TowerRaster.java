package projektkurs.simulation.tower.raster;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.raster.logic.TowerLogic;

public abstract class TowerRaster {

    public static final TowerRasterGrass grass = new TowerRasterGrass();
    public static final TowerRasterPath path = new TowerRasterPath();
    public static final TowerRaster gateway = new TowerRasterGateway();

    public TowerLogic createLogic(TowerDefenseBoard board, int x, int y) {
        return null;
    }

    public abstract void render(TowerDefenseBoard board, int x, int y, Screen screen, int posX, int posY);

}
