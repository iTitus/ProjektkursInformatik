package projektkurs.simulation.tower.raster;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.raster.logic.TieredTowerLogic;
import projektkurs.simulation.tower.raster.logic.TowerLogic;

public abstract class TowerRasterTowerBase extends TowerRaster {

    @Override
    public abstract TieredTowerLogic createLogic(TowerDefenseBoard board, int x, int y);

    @Override
    public boolean isTower() {
        return true;
    }

    @Override
    public int getRequiredRenderPasses() {
        return 2;
    }

    @Override
    public void render(TowerDefenseBoard board, int x, int y, Screen screen, int posX, int posY, int pass) {
        TowerLogic towerLogic = board.getTowerLogic(x, y);
        if (towerLogic instanceof TieredTowerLogic && !towerLogic.isInvalid()) {
            if (pass == 0) {
                ((TieredTowerLogic) towerLogic).renderBackground(screen, posX, posY);
            } else if (pass == 1) {
                ((TieredTowerLogic) towerLogic).renderOverlay(screen, posX, posY);
            }
        }
    }

}
