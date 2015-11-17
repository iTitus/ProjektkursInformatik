package projektkurs.simulation.tower.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.gui.ElementTowerDefenseBoard;
import projektkurs.simulation.tower.raster.logic.PathLogic;
import projektkurs.simulation.tower.raster.logic.TowerLogic;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class Monster extends TowerEntity {

    private final double speed;
    private final int color;
    private double targetX, targetY;

    public Monster(TowerDefenseBoard board, double speed, int color) {
        this(board, 1, 1, speed, color);
    }

    protected Monster(TowerDefenseBoard board, double sizeX, double sizeY, double speed, int color) {
        super(board, board.getStartX() + 0.5 - sizeX / 2, board.getStartY() + 0.5 - sizeY / 2, sizeX, sizeY);
        this.speed = speed;
        this.color = color;
        this.targetX = -1;
        this.targetY = -1;
    }

    @Override
    public void update() {
        if (MathUtil.isInside(this, board.getEnd())) {
            board.subtractLife();
            setDead();
        } else {
            if (targetX < 0 || targetY < 0 || isOutsideOldRaster()) {
                TowerLogic towerLogic = board.getTowerLogic(MathUtil.floorAdd(x, sizeX / 2), MathUtil.floorAdd(y, sizeY / 2));
                if (towerLogic instanceof PathLogic) {
                    PathLogic pathLogic = (PathLogic) towerLogic;
                    targetX = pathLogic.getX() + pathLogic.getDirection().getOffsetX() + 0.5;
                    targetY = pathLogic.getY() + pathLogic.getDirection().getOffsetY() + 0.5;
                }
            }
            double d = targetX - (x + sizeX / 2);
            if (d != 0) {
                if (MathUtil.abs(d) <= speed) {
                    x = targetX - sizeX / 2;
                } else {
                    x += MathUtil.signum(d) * speed;
                }
            }
            d = targetY - (y + sizeY / 2);
            if (d != 0) {
                if (MathUtil.abs(d) <= speed) {
                    y = targetY - sizeY / 2;
                } else {
                    y += MathUtil.signum(d) * speed;
                }
            }
        }
    }

    private boolean isOutsideOldRaster() {
        double targetX_ = -1;
        double targetY_ = -1;
        TowerLogic towerLogic = board.getTowerLogic(MathUtil.floorAdd(x, sizeX / 2), MathUtil.floorAdd(y, sizeY / 2));
        if (towerLogic instanceof PathLogic) {
            PathLogic pathLogic = (PathLogic) towerLogic;
            targetX_ = pathLogic.getX() + pathLogic.getDirection().getOffsetX() + 0.5;
            targetY_ = pathLogic.getY() + pathLogic.getDirection().getOffsetY() + 0.5;
        }
        return targetX != targetX_ || targetY != targetY_;
    }

    @Override
    public void render(Screen screen, int posX, int posY) {
        RenderUtil.drawFilledRectangle(screen, posX + MathUtil.floor(ElementTowerDefenseBoard.SIZE * x), posY + MathUtil.floor(ElementTowerDefenseBoard.SIZE * y), MathUtil.floor(ElementTowerDefenseBoard.SIZE * sizeX), MathUtil.floor(ElementTowerDefenseBoard.SIZE * sizeY), color);
    }
}
