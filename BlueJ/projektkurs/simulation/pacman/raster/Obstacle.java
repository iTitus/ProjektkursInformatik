package projektkurs.simulation.pacman.raster;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.util.RenderUtil;

public class Obstacle extends PacmanRaster {

    public Obstacle() {
        setSolid(true);
    }

    @Override
    public void render(PacmanBoard board, int x, int y, Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawFilledRectangle(screen, offsetX + ElementPacmanBoard.SIZE * x, offsetY + ElementPacmanBoard.SIZE * y, ElementPacmanBoard.SIZE, ElementPacmanBoard.SIZE, 0x0000FF);
    }

}
