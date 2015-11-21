package projektkurs.simulation.tower.raster;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.raster.logic.SimpleShooterLogic;
import projektkurs.simulation.tower.raster.logic.TieredTowerLogic;
import projektkurs.simulation.tower.raster.logic.TowerLogic;

public abstract class TowerRaster {

    public static final TowerRasterGrass grass = new TowerRasterGrass();
    public static final TowerRasterPath path = new TowerRasterPath();
    public static final TowerRasterTowerBase simpleShooter = new TowerRasterTowerBase() {

        @Override
        public TieredTowerLogic createLogic(TowerDefenseBoard board, int x, int y) {
            return new SimpleShooterLogic(board, x, y);
        }

    };

    public boolean canPlaceTower() {
        return false;
    }

    public TowerLogic createLogic(TowerDefenseBoard board, int x, int y) {
        return null;
    }

    public boolean isTower() {
        return false;
    }

    public abstract void render(TowerDefenseBoard board, int x, int y, Screen screen, int posX, int posY);

}
