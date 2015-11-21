package projektkurs.simulation.tower.raster.logic;

import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.util.IUpdatable;

public abstract class TowerLogic implements IUpdatable {

    private boolean invalid = false;
    protected final TowerDefenseBoard board;
    protected final int x, y;

    public TowerLogic(TowerDefenseBoard board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean canUpdate() {
        return !invalid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid() {
        invalid = true;
    }

}
