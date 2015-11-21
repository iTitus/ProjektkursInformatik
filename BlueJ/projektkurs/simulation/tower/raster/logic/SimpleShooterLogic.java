package projektkurs.simulation.tower.raster.logic;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.TowerType;
import projektkurs.simulation.tower.entity.Monster;
import projektkurs.simulation.tower.entity.Projectile;
import projektkurs.util.MathUtil;

public class SimpleShooterLogic extends TieredTowerLogic {

    private Monster target;

    public SimpleShooterLogic(TowerDefenseBoard board, int x, int y) {
        super(board, x, y, TowerType.SIMPLE_SHOOTER);
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
            return false;
        }

        board.spawn(new Projectile(board, this, target));

        return true;

    }

    @Override
    public void render(TowerDefenseBoard board, int x, int y, Screen screen, int posX, int posY) {
        // NO-OP
    }
}
