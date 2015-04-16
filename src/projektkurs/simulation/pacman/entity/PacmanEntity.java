package projektkurs.simulation.pacman.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.util.IHasPositionAndSize;
import projektkurs.util.IUpdatable;
import projektkurs.util.MathUtil;

public abstract class PacmanEntity implements IUpdatable, IHasPositionAndSize<Double, Double> {

    private boolean isDead = false;
    protected PacmanBoard board;
    protected double sizeX;
    protected double sizeY;
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

    @Override
    public Double getPosX() {
        return x;
    }

    @Override
    public Double getPosY() {
        return y;
    }

    @Override
    public Double getSizeX() {
        return sizeX;
    }

    @Override
    public Double getSizeY() {
        return sizeY;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isInside(double x, double y, double sizeX, double sizeY) {
        return MathUtil.isInside(this.x, this.y, this.sizeX, this.sizeY, x, y, sizeX, sizeY);
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
        onDeath();
    }

    @Override
    public void setPosition(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setSize(Double sizeX, Double sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public void update() {
        // NO-OP
    }

}
