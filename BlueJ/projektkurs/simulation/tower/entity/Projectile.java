package projektkurs.simulation.tower.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.gui.ElementTowerDefenseBoard;
import projektkurs.simulation.tower.raster.logic.TieredTowerLogic;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;
import projektkurs.util.Vector2d;

public class Projectile extends TowerEntity {

    private final int damage, color;
    private final TieredTowerLogic shooter;
    private Vector2d speed;
    private final Monster target;

    public Projectile(TowerDefenseBoard board, double x, double y, double sizeX, double sizeY, TieredTowerLogic shooter, Monster target) {
        super(board, x, y, sizeX, sizeY);
        this.shooter = shooter;
        this.target = target;
        color = shooter.getColor();
        damage = shooter.getDamage();
        speed = new Vector2d(target.getPosX() + target.getSizeX() / 2 - (x + sizeX / 2), target.getPosY() + target.getSizeY() / 2 - (y + sizeY / 2)).normalize().mul(shooter.getSpeed());
    }

    public Projectile(TowerDefenseBoard board, TieredTowerLogic shooter, Monster target) {
        this(board, shooter.getX() + 0.5 - 0.125 / 2, shooter.getY() + 0.5 - 0.125 / 2, 0.125, 0.125, shooter, target);
    }

    public int getColor() {
        return color;
    }

    public TieredTowerLogic getShooter() {
        return shooter;
    }

    public Vector2d getSpeed() {
        return speed;
    }

    public Monster getTarget() {
        return target;
    }

    @Override
    public void render(Screen screen, int posX, int posY) {
        RenderUtil.drawFilledRectangle(screen, posX + MathUtil.floor(ElementTowerDefenseBoard.SIZE * x), posY + MathUtil.floor(ElementTowerDefenseBoard.SIZE * y), MathUtil.floor(ElementTowerDefenseBoard.SIZE * sizeX), MathUtil.floor(ElementTowerDefenseBoard.SIZE * sizeY), color);
    }

    @Override
    public void update() {
        if (target.isDead()) {
            setDead();
            return;
        }
        double dx = target.getPosX() + target.getSizeX() / 2 - (x + sizeX / 2);
        double dy = target.getPosY() + target.getSizeY() / 2 - (y + sizeY / 2);
        speed = new Vector2d(dx, dy).normalize().mul(shooter.getSpeed());
        if (MathUtil.abs(dx) <= MathUtil.abs(speed.getX())) {
            x += dx;
        } else {
            x += speed.getX();
        }
        if (MathUtil.abs(dy) <= MathUtil.abs(speed.getY())) {
            y += dy;
        } else {
            y += speed.getY();
        }
        if (MathUtil.isInside(this, target)) {
            target.damage(damage);
            setDead();
        }
    }

}
