package projektkurs.simulation.tower.raster.logic;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerAttackMode;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.TowerType;

public abstract class TieredTowerLogic extends TowerLogic {

    private int tier, cooldown;
    private final TowerType towerType;
    protected TowerAttackMode mode;

    public TieredTowerLogic(TowerDefenseBoard board, int x, int y, TowerType towerType) {
        super(board, x, y);
        mode = TowerAttackMode.FIRST;
        tier = 1;
        this.towerType = towerType;
    }

    public int getColor() {
        return towerType.getColor(tier);
    }

    public int getDamage() {
        return towerType.getDamage(tier);
    }

    public int getFrequency() {
        return towerType.getFrequency(tier);
    }

    public int getMaxTier() {
        return towerType.getMaxTier();
    }

    public TowerAttackMode getMode() {
        return mode;
    }

    public String getName() {
        return towerType.getName();
    }

    public double getRange() {
        return towerType.getRange(tier);
    }

    public double getSpeed() {
        return towerType.getSpeed(tier);
    }

    public int getTier() {
        return tier;
    }

    public int getUpgradeCost() {
        return towerType.getUpgradeCost(tier);
    }

    public abstract void renderOverlay(Screen screen, int posX, int posY);

    public abstract void renderBackground(Screen screen, int posX, int posY);

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public void update() {
        if (cooldown > 0) {
            cooldown--;
        }
        if (cooldown <= 0) {
            cooldown = 0;
            if (doUpdate()) {
                cooldown = getFrequency();
            }
        }
    }

    protected abstract boolean doUpdate();

}
