package projektkurs.simulation.pacman.entity;

import java.util.List;

import projektkurs.Main;
import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.simulation.pacman.raster.PacmanRaster;
import projektkurs.util.Direction;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class Pacman extends PacmanEntity {

    private static final double SPEED = MathUtil.inverse(ElementPacmanBoard.SIZE);

    private Direction direction = Direction.UNKNOWN;
    private Direction nextDirection = Direction.UNKNOWN;

    public Pacman(PacmanBoard board, double x, double y) {
        super(board, x, y, 1, 1);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    @Override
    public void onCollide(PacmanEntity e) {
        if (e instanceof Ghost) {
            if (board.isSuperMode()) {
                e.setDead();
            } else {
                setDead();
            }
        }
    }

    @Override
    public void onDeath() {
        Main.closeGui();
    }

    @Override
    public void render(Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawFilledRectangle(screen, offsetX + 1 + MathUtil.floor(ElementPacmanBoard.SIZE * x), offsetY + 1 + MathUtil.floor(ElementPacmanBoard.SIZE * y), ElementPacmanBoard.SIZE - 2, ElementPacmanBoard.SIZE - 2, 0xFFFF00);
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    @Override
    public void update() {
        if (nextDirection != Direction.UNKNOWN) {
            int rX1 = MathUtil.floor(x + nextDirection.getOffsetX() * SPEED);
            int rY1 = MathUtil.floor(y + nextDirection.getOffsetY() * SPEED);
            PacmanRaster r1 = board.getPacmanRaster(rX1, rY1);
            int rX2 = MathUtil.floor(x + sizeX + nextDirection.getOffsetX() * SPEED);
            int rY2 = MathUtil.floor(y + nextDirection.getOffsetY() * SPEED);
            PacmanRaster r2 = board.getPacmanRaster(rX2, rY2);
            int rX3 = MathUtil.floor(x + nextDirection.getOffsetX() * SPEED);
            int rY3 = MathUtil.floor(y + sizeY + nextDirection.getOffsetY() * SPEED);
            PacmanRaster r3 = board.getPacmanRaster(rX3, rY3);
            int rX4 = MathUtil.floor(x + sizeX + nextDirection.getOffsetX() * SPEED);
            int rY4 = MathUtil.floor(y + sizeY + nextDirection.getOffsetY() * SPEED);
            PacmanRaster r4 = board.getPacmanRaster(rX4, rY4);
            if ((r1 == null || !r1.isSolid()) && (r2 == null || !r2.isSolid()) && (r3 == null || !r3.isSolid()) && (r4 == null || !r4.isSolid())) {
                direction = nextDirection;
            }
            nextDirection = Direction.UNKNOWN;
        }
        if (direction != Direction.UNKNOWN) {
            int rX1 = MathUtil.floor(x + direction.getOffsetX() * SPEED);
            int rY1 = MathUtil.floor(y + direction.getOffsetY() * SPEED);
            PacmanRaster r1 = board.getPacmanRaster(rX1, rY1);
            int rX2 = MathUtil.floor(x + sizeX + direction.getOffsetX() * SPEED);
            int rY2 = MathUtil.floor(y + direction.getOffsetY() * SPEED);
            PacmanRaster r2 = board.getPacmanRaster(rX2, rY2);
            int rX3 = MathUtil.floor(x + direction.getOffsetX() * SPEED);
            int rY3 = MathUtil.floor(y + sizeY + direction.getOffsetY() * SPEED);
            PacmanRaster r3 = board.getPacmanRaster(rX3, rY3);
            int rX4 = MathUtil.floor(x + sizeX + direction.getOffsetX() * SPEED);
            int rY4 = MathUtil.floor(y + sizeY + direction.getOffsetY() * SPEED);
            PacmanRaster r4 = board.getPacmanRaster(rX4, rY4);
            if ((r1 == null || !r1.isSolid()) && (r2 == null || !r2.isSolid()) && (r3 == null || !r3.isSolid()) && (r4 == null || !r4.isSolid())) {
                move(direction.getOffsetX() * SPEED, direction.getOffsetY() * SPEED);
                if (r1 != null) {
                    r1.onWalkOn(board, rX1, rY1, this);
                }
                if (r2 != null) {
                    r2.onWalkOn(board, rX2, rY2, this);
                }
                if (r3 != null) {
                    r3.onWalkOn(board, rX3, rY3, this);
                }
                if (r4 != null) {
                    r4.onWalkOn(board, rX4, rY4, this);
                }
                List<PacmanEntity> entities = board.getPacmanEntities(x + direction.getOffsetX() * SPEED, y + direction.getOffsetY() * SPEED, sizeX, sizeY);
                if (entities != null) {
                    for (PacmanEntity e : entities) {
                        if (e != null) {
                            e.onCollide(this);
                        }
                    }
                }
            }
        }
    }
}
