package projektkurs.simulation.tower.raster;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.gui.ElementTowerDefenseBoard;
import projektkurs.simulation.tower.raster.logic.PathLogic;
import projektkurs.simulation.tower.raster.logic.TowerLogic;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class TowerRasterPath extends TowerRaster {

    @Override
    public TowerLogic createLogic(TowerDefenseBoard board, int x, int y) {
        return new PathLogic(board, x, y);
    }

    @Override
    public void render(TowerDefenseBoard board, int x, int y, Screen screen, int posX, int posY, int pass) {
        RenderUtil.drawFilledRectangle(screen, posX + MathUtil.floor(ElementTowerDefenseBoard.SIZE * x), posY + MathUtil.floor(ElementTowerDefenseBoard.SIZE * y), ElementTowerDefenseBoard.SIZE, ElementTowerDefenseBoard.SIZE, 0xDDDAAA);
    }

}
