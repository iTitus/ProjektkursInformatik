package projektkurs.simulation.pacman.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public abstract class Ghost extends PacmanEntity {

    public static final int POINTS = 100;
    protected int targetX, targetY;

    public Ghost(PacmanBoard board, double x, double y) {
        super(board, x, y, 1, 1);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public abstract int getColor();

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void moveTarget(int dtargetX, int dtargetY) {
        targetX += dtargetX;
        targetY += dtargetY;
    }

    @Override
    public void onCollide(PacmanEntity e) {
        if (board.isSuperMode()) {
            onDeath();
        } else {
            e.onDeath();
        }
    }

    @Override
    public void onDeath() {
        board.increaseScore(POINTS * board.getMultiplicator());
        board.increaseMultiplicator();
        targetX = board.getGhostSpawnX();
        targetY = board.getGhostSpawnY();
    }

    @Override
    public void render(Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawFilledRectangle(screen, offsetX + 1 + MathUtil.floor(ElementPacmanBoard.SIZE * x), offsetY + 1 + MathUtil.floor(ElementPacmanBoard.SIZE * y), ElementPacmanBoard.SIZE - 2, ElementPacmanBoard.SIZE - 2, getColor());
    }

    public void setTarget(int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    @Override
    public abstract void update();
}
