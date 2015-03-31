package projektkurs.simulation.pacman.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.IMortal;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.util.IHasPosition;
import projektkurs.util.IUpdatable;

public abstract class PacmanEntity implements IUpdatable, IMortal, IHasPosition {

    protected PacmanBoard board;
    protected int x, y;

    public PacmanEntity(PacmanBoard board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public int getPosX() {
        return x;
    }

    @Override
    public int getPosY() {
        return y;
    }

    @Override
    public void onDeath() {
        // NO-OP
    }

    public abstract void render(Screen screen, int offsetX, int offsetY);

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean tryWalkOn(PacmanEntity e);

    @Override
    public void update() {
        // NO-OP
    }

}
