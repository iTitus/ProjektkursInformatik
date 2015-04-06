package projektkurs.simulation.pacman.raster;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.entity.PacmanEntity;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.util.RenderUtil;

public class SmallCoin extends PacmanRaster {

    public static final int POINTS = 5;

    @Override
    public void onWalkOn(PacmanBoard board, int x, int y, PacmanEntity e) {
        board.increaseScore(POINTS);
        board.setPacmanRaster(x, y, PacmanRaster.emptySpace);
    }

    @Override
    public void render(PacmanBoard board, int x, int y, Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawFilledRectangle(screen, offsetX + ElementPacmanBoard.SIZE * x, offsetY + ElementPacmanBoard.SIZE * y, ElementPacmanBoard.SIZE, ElementPacmanBoard.SIZE);
        RenderUtil.drawFilledRectangle(screen, offsetX + 7 + ElementPacmanBoard.SIZE * x, offsetY + 7 + ElementPacmanBoard.SIZE * y, 2, 2, 0xFFFF00);
    }
}
