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

    private Direction d = Direction.UNKNOWN;

    public Pacman(PacmanBoard board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public Direction getDirection() {
        return d;
    }

    @Override
    public void onCollide(PacmanEntity e) {
        if (board.isSuperMode()) {
            e.onDeath();
        } else {
            onDeath();
        }
    }

    @Override
    public void onDeath() {
        Main.closeGui();
    }

    @Override
    public void render(Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawRectangle(screen, offsetX + 1 + ElementPacmanBoard.SIZE * MathUtil.round(x), offsetY + 1 + ElementPacmanBoard.SIZE * MathUtil.round(y), ElementPacmanBoard.SIZE - 2, ElementPacmanBoard.SIZE - 2, 0xFFFF00);
    }

    public void setDirection(Direction direction) {
        d = direction;
    }

    @Override
    public void update() {
        int rX = MathUtil.round(x) + d.getOffsetX();
        int rY = MathUtil.round(y) + d.getOffsetY();
        PacmanRaster r = board.getPacmanRaster(rX, rY);
        if (!r.isSolid()) {
            r.onWalkOn(board, rX, rY, this);
            List<PacmanEntity> entities = board.getPacmanEntities(x + d.getOffsetX(), y + d.getOffsetY());
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
