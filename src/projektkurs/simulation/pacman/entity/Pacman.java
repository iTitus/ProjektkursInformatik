package projektkurs.simulation.pacman.entity;

import java.util.List;

import projektkurs.Main;
import projektkurs.render.Screen;
import projektkurs.simulation.pacman.GhostMode;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.simulation.pacman.raster.PacmanRaster;
import projektkurs.util.Direction;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class Pacman extends PacmanEntity {

    public static final double SPEED = MathUtil.inverse(ElementPacmanBoard.SIZE);

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
            GhostMode mode = ((Ghost) e).getMode();
            if (mode == GhostMode.FRIGHTENED) {
                e.setDead();
            } else if (mode != GhostMode.RETURNING_HOME) {
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
        RenderUtil.drawFilledRectangle(screen, offsetX + MathUtil.floor(ElementPacmanBoard.SIZE * x), offsetY + MathUtil.floor(ElementPacmanBoard.SIZE * y), ElementPacmanBoard.SIZE, ElementPacmanBoard.SIZE, 0xFFFF00);
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

            if ((r1 == null || !(r1.isSolid() && MathUtil.isInside(rX1, rY1, 1, 1, x + nextDirection.getOffsetX() * SPEED, y + nextDirection.getOffsetY(), sizeX, sizeY)))
                    && (r2 == null || !(r2.isSolid() && MathUtil.isInside(rX2, rY2, 1, 1, x + nextDirection.getOffsetX() * SPEED, y + nextDirection.getOffsetY(), sizeX, sizeY)))
                    && (r3 == null || !(r3.isSolid() && MathUtil.isInside(rX3, rY3, 1, 1, x + nextDirection.getOffsetX() * SPEED, y + nextDirection.getOffsetY(), sizeX, sizeY)))
                    && (r4 == null || !(r4.isSolid() && MathUtil.isInside(rX4, rY4, 1, 1, x + nextDirection.getOffsetX() * SPEED, y + nextDirection.getOffsetY(), sizeX, sizeY)))) {
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

            double dx = 0;
            double dy = 0;
            if ((r1 == null || !(r1.isSolid() && MathUtil.isInside(rX1, rY1, 1, 1, x + direction.getOffsetX() * SPEED, y + direction.getOffsetY() * SPEED, sizeX, sizeY)))
                    && (r2 == null || !(r2.isSolid() && MathUtil.isInside(rX2, rY2, 1, 1, x + direction.getOffsetX() * SPEED, y + direction.getOffsetY() * SPEED, sizeX, sizeY)))
                    && (r3 == null || !(r3.isSolid() && MathUtil.isInside(rX3, rY3, 1, 1, x + direction.getOffsetX() * SPEED, y + direction.getOffsetY() * SPEED, sizeX, sizeY)))
                    && (r4 == null || !(r4.isSolid() && MathUtil.isInside(rX4, rY4, 1, 1, x + direction.getOffsetX() * SPEED, y + direction.getOffsetY() * SPEED, sizeX, sizeY)))) {
                dx = direction.getOffsetX() * SPEED;
                dy = direction.getOffsetY() * SPEED;
            } else {
                dx = direction.getOffsetX() != 0 ? direction.getOffsetX() < 0 ? rX1 + 1 - x : rX2 - (x + sizeX) : 0;
                dy = direction.getOffsetY() != 0 ? direction.getOffsetY() < 0 ? rY1 + 1 - y : rY3 - (y + sizeY) : 0;
            }
            move(dx, dy);
            if (r1 != null) {
                r1.onWalkOn(board, rX1, rY1, this);
            }
            if (r2 != null && (rX2 != rX1 || rY2 != rY1)) {
                r2.onWalkOn(board, rX2, rY2, this);
            }
            if (r3 != null && (rX3 != rX1 || rY3 != rY1) && (rX3 != rX2 || rY3 != rY2)) {
                r3.onWalkOn(board, rX3, rY3, this);
            }
            if (r4 != null && (rX4 != rX1 || rY4 != rY1) && (rX4 != rX2 || rY4 != rY2) && (rX4 != rX3 || rY4 != rY3)) {
                r4.onWalkOn(board, rX4, rY4, this);
            }
            List<PacmanEntity> entities = board.getPacmanEntities(x, y, sizeX, sizeY);
            if (entities != null) {
                for (PacmanEntity e : entities) {
                    if (e != null && e != this) {
                        e.onCollide(this);
                    }
                }
            }

        }
    }
}
