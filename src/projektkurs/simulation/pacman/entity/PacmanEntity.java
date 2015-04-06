package projektkurs.simulation.pacman.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.util.IUpdatable;

public abstract class PacmanEntity implements IUpdatable {

    private boolean isDead = false;
    protected PacmanBoard board;
    protected final double sizeX, sizeY;
    protected double x, y;

    public PacmanEntity(PacmanBoard board, double x, double y, double sizeX, double sizeY) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    public double getPosX() {
        return x;
    }

    public double getPosY() {
        return y;
    }

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public boolean isDead() {
        return isDead;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void onCollide(PacmanEntity e) {
        // NO-OP
    }

    public void onDeath() {
        // NO-OP
    }

    public abstract void render(Screen screen, int offsetX, int offsetY);

    public void setDead() {
        isDead = true;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        // NO-OP
    }

}
