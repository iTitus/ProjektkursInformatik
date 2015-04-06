package projektkurs.simulation.life;

import projektkurs.render.Screen;
import projektkurs.simulation.life.gui.ElementGameOfLife;
import projektkurs.util.Direction;
import projektkurs.util.IUpdatable;
import projektkurs.util.RenderUtil;

/**
 * Conway's Game of Life
 */
public class GameOfLifeBoard implements IUpdatable {

    private int[][] board;
    private boolean canUpdate = false;
    private int generation, life;
    private final int sizeX, sizeY;

    public GameOfLifeBoard(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        board = new int[sizeX][sizeY];
    }

    @Override
    public boolean canUpdate() {
        return canUpdate;
    }

    public int getGeneration() {
        return generation;
    }

    public int getLife() {
        return life;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public boolean isAlive(int x, int y) {
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            return board[x][y] != 0;
        }
        return false;
    }

    public void render(Screen screen, int offsetX, int offsetY) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (isAlive(x, y)) {
                    RenderUtil.drawFilledRectangle(screen, offsetX + x * ElementGameOfLife.SIZE, offsetY + y * ElementGameOfLife.SIZE, ElementGameOfLife.SIZE, ElementGameOfLife.SIZE, 0x0000FF);
                }
            }
        }

    }

    public void reset() {
        board = new int[sizeX][sizeY];
        generation = 0;
        life = 0;
        canUpdate = false;
    }

    public void setBoard(int x, int y, boolean toSet) {
        board[x][y] = toSet ? 1 : 0;
    }

    public void setCanUpdate(boolean b) {
        canUpdate = b;
    }

    @Override
    public void update() {
        generation++;
        life = 0;
        int[][] temp = new int[sizeX][sizeY];
        for (int x = 0; x < temp.length; x++) {
            for (int y = 0; y < temp[x].length; y++) {
                int i = 0;
                for (Direction d : Direction.VALID_DIRECTIONS) {
                    if (isAlive(x + d.getOffsetX(), y + d.getOffsetY())) {
                        i++;
                    }
                }
                if (i == 3) {
                    temp[x][y] = 1;
                    life++;
                } else if (isAlive(x, y) && i == 2) {
                    temp[x][y] = 1;
                    life++;
                }
            }
        }
        board = temp;
    }

}
