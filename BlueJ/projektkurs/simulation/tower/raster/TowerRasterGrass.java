package projektkurs.simulation.tower.raster;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.gui.ElementTowerDefenseBoard;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class TowerRasterGrass extends TowerRaster {

    @Override
    public boolean canPlaceTower() {
        return true;
    }

    @Override
    public void render(TowerDefenseBoard board, int x, int y, Screen screen, int posX, int posY, int pass) {
        RenderUtil.drawFilledRectangle(screen, posX + MathUtil.floor(ElementTowerDefenseBoard.SIZE * x), posY + MathUtil.floor(ElementTowerDefenseBoard.SIZE * y), ElementTowerDefenseBoard.SIZE, ElementTowerDefenseBoard.SIZE, 0x00FF00);
    }

}
