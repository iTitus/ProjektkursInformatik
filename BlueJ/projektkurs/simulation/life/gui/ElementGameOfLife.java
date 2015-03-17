package projektkurs.simulation.life.gui;

import projektkurs.gui.element.Element;
import projektkurs.render.Screen;
import projektkurs.simulation.life.GameOfLifeBoard;
import projektkurs.util.RenderUtil;

/**
 *
 */
public class ElementGameOfLife extends Element {

    public static final int SIZE = 4;
    private final GameOfLifeBoard lifeBoard;

    public ElementGameOfLife(int posX, int posY, int sizeX, int sizeY, int id) {
        super(posX, posY, sizeX * SIZE, sizeY * SIZE, id, null);
        lifeBoard = new GameOfLifeBoard(sizeX, sizeY);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void render(Screen screen) {
        lifeBoard.render(screen, posX, posY);
        RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
    }

    @Override
    public void update() {
        lifeBoard.update();
    }

}
