package projektkurs.simulation.tower.raster.logic;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.TowerType;
import projektkurs.simulation.tower.entity.Monster;
import projektkurs.simulation.tower.entity.SimpleProjectile;
import projektkurs.simulation.tower.gui.ElementTowerDefenseBoard;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class SimpleShooterLogic extends TieredTowerLogic {

    private static final double rotationSpeed = 3;

    private double angle;
    private Monster target;

    public SimpleShooterLogic(TowerDefenseBoard board, int x, int y) {
        super(board, x, y, TowerType.SIMPLE_SHOOTER);
        angle = MathUtil.randomInt(0, 360);
    }

    @Override
    public boolean doUpdate() {
        double range = getRange();

        if (target != null && (target.isDead() || MathUtil.getDistanceSq(x, y, target.getPosX(), target.getPosY()) > range * range)) {
            target = null;
        }
        if (target == null) {
            target = board.findTarget(this);
        }
        if (target == null) {
            angle += rotationSpeed;
            if (angle >= 360) {
                angle -= 360;
            }
            if (angle < 0) {
                angle += 360;
            }
            return false;
        }

        angle = getAngleToTarget();
        board.spawn(new SimpleProjectile(board, this, target));

        return true;

    }

    public double getAngle() {
        return angle;
    }

    @Override
    public void renderBackground(Screen screen, int posX, int posY) {
        RenderUtil.drawFilledCircle(screen, posX + MathUtil.floor((x + 0.5) * ElementTowerDefenseBoard.SIZE), posY + MathUtil.floor((y + 0.5) * ElementTowerDefenseBoard.SIZE), ElementTowerDefenseBoard.SIZE / 2 - 1);
    }

    @Override
    public void renderOverlay(Screen screen, int posX, int posY) {
        double deg = angle;
        double r = 3;
        double sin = r * MathUtil.sinDeg(deg);
        double cos = r * MathUtil.cosDeg(deg);
        int cX = MathUtil.floor(posX + (x + 0.5) * ElementTowerDefenseBoard.SIZE);
        int cY = MathUtil.floor(posY + (y + 0.5) * ElementTowerDefenseBoard.SIZE);
        // Font.drawString(screen, String.format("Angle: %.2f", deg), cX - 16, cY - 32);

        // RenderUtil.drawLine(screen, (int) (posX + (x + 0.5 + sin) * ElementTowerDefenseBoard.SIZE), (int) (posY + (y + 0.5 + cos) * ElementTowerDefenseBoard.SIZE), (int) (posX + (x + 0.5 - sin) * ElementTowerDefenseBoard.SIZE), (int) (posY + (y + 0.5 - cos) * ElementTowerDefenseBoard.SIZE));

        RenderUtil.drawLine(screen, cX, cY, cX + MathUtil.floor(sin * ElementTowerDefenseBoard.SIZE), cY + MathUtil.floor(cos * ElementTowerDefenseBoard.SIZE), 0xFF0000);
        RenderUtil.drawLine(screen, cX, cY, cX - MathUtil.floor(sin * ElementTowerDefenseBoard.SIZE), cY - MathUtil.floor(cos * ElementTowerDefenseBoard.SIZE), 0x0000FF);

        RenderUtil.drawFilledRectangle(screen, cX - 1, cY - 1, 3, 3, 0x00FF00);
        RenderUtil.drawFilledRectangle(screen, cX + MathUtil.floor(sin * ElementTowerDefenseBoard.SIZE) - 1, cY + MathUtil.floor(cos * ElementTowerDefenseBoard.SIZE) - 1, 3, 3, 0xFF0000);
        RenderUtil.drawFilledRectangle(screen, cX - MathUtil.floor(sin * ElementTowerDefenseBoard.SIZE) - 1, cY - MathUtil.floor(cos * ElementTowerDefenseBoard.SIZE) - 1, 3, 3, 0x0000FF);
    }

    private double getAngleToTarget() {
        if (target == null || target.isDead()) {
            return 0;
        }
        return MathUtil.atan2Deg(target.getPosY() + target.getSizeY() / 2 - (y + 0.5), target.getPosX() + target.getSizeX() / 2 - (x + 0.5));
    }
}
