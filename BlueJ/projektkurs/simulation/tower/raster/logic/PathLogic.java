package projektkurs.simulation.tower.raster.logic;

import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.util.Direction;

public class PathLogic extends TowerLogic {

    private Direction direction;

    public PathLogic(TowerDefenseBoard board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void update() {
        // NO-OP
    }

}
