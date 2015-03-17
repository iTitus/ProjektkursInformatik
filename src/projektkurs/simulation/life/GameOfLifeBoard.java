package projektkurs.simulation.life;

import projektkurs.render.Screen;
import projektkurs.util.IUpdatable;

/**
 * Conway's Game of Life
 */
public class GameOfLifeBoard implements IUpdatable {

    private final int sizeX, sizeY;

    public GameOfLifeBoard(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void render(Screen screen, int offsetX, int offsetY) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

}
