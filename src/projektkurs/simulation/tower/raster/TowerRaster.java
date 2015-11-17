package projektkurs.simulation.tower.raster;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.gui.ElementTowerDefenseBoard;
import projektkurs.simulation.tower.raster.logic.TowerLogic;

public abstract class TowerRaster {

    public TowerLogic createLogic(ElementTowerDefenseBoard board, int x, int y) {
        return null;
    }

    public abstract void render(TowerDefenseBoard board, int x, int y, Screen screen, int posX, int posY);

}
