package projektkurs.simulation.tower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.entity.Monster;
import projektkurs.simulation.tower.entity.TowerEntity;
import projektkurs.simulation.tower.raster.TowerRaster;
import projektkurs.simulation.tower.raster.logic.TieredTowerLogic;
import projektkurs.simulation.tower.raster.logic.TowerLogic;
import projektkurs.util.IUpdatable;
import projektkurs.util.MathUtil;
import projektkurs.util.Rectangle;

public class TowerDefenseBoard implements IUpdatable {

    private final TowerRaster[][] board;
    private final Rectangle end;
    private final int sizeX, sizeY;
    private int startX, startY, life, ticks, credits;
    private final Set<TowerEntity> towerEntities;
    private final Set<TowerLogic> towerLogics;

    public TowerDefenseBoard(TowerDefenseBoardType boardType) {
        sizeX = boardType.getSizeX();
        sizeY = boardType.getSizeY();
        board = new TowerRaster[sizeX][sizeY];
        towerLogics = new HashSet<TowerLogic>();
        towerEntities = new HashSet<TowerEntity>();
        life = 100;
        end = new Rectangle(-1, -1, 1, 1);
        boardType.generate(this);
        credits = boardType.getStartingCredits();
    }

    public void addCredits(int credit) {
        credits += credit;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public Monster findTarget(TieredTowerLogic towerLogic) {
        double range = towerLogic.getRange();
        List<Monster> list = new ArrayList<Monster>();
        Iterator<TowerEntity> iterator = towerEntities.iterator();
        while (iterator.hasNext()) {
            TowerEntity towerEntity = iterator.next();
            if (towerEntity != null && !towerEntity.isDead() && towerEntity instanceof Monster) {
                Monster m = (Monster) towerEntity;
                if (MathUtil.getDistanceSq(towerLogic.getX() + 0.5, towerLogic.getY() + 0.5, m.getPosX() + m.getSizeX() / 2, m.getPosY() + m.getSizeY() / 2) <= range * range) {
                    list.add(m);
                }
            }
        }
        if (list.isEmpty()) {
            return null;
        }
        Collections.sort(list, towerLogic.getMode());
        return list.get(0);
    }

    public int getCredits() {
        return credits;
    }

    public Rectangle getEnd() {
        return end;
    }

    public int getLife() {
        return life;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getTicks() {
        return ticks;
    }

    public TowerLogic getTowerLogic(int x, int y) {
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            Iterator<TowerLogic> iterator = towerLogics.iterator();
            while (iterator.hasNext()) {
                TowerLogic towerLogic = iterator.next();
                if (towerLogic != null && towerLogic.getX() == x && towerLogic.getY() == y && !towerLogic.isInvalid()) {
                    return towerLogic;
                }
            }
        }
        return null;
    }

    public TowerRaster getTowerRaster(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            return null;
        }
        return board[x][y];
    }

    public void render(Screen screen, int posX, int posY) {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                TowerRaster towerRaster = getTowerRaster(x, y);
                if (towerRaster != null) {
                    towerRaster.render(this, x, y, screen, posX, posY);
                }
            }
        }
        for (TowerEntity towerEntity : towerEntities) {
            if (towerEntity != null) {
                towerEntity.render(screen, posX, posY);
            }
        }
    }

    public void setEnd(double endX, double endY) {
        end.setPosition(endX, endY);
    }

    public void setStart(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void setTowerRaster(int x, int y, TowerRaster towerRaster) {
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            removeTowerLogic(x, y);
            board[x][y] = towerRaster;
            addTowerLogic(x, y);
        }
    }

    public void spawn(TowerEntity towerEntity) {
        if (towerEntity != null) {
            towerEntities.add(towerEntity);
        }
    }

    public void subtractLife() {
        life--;
    }

    @Override
    public void update() {
        ticks++;
        for (TowerLogic towerLogic : towerLogics) {
            if (towerLogic != null && towerLogic.canUpdate()) {
                towerLogic.update();
            }
        }
        Iterator<TowerEntity> iterator = towerEntities.iterator();
        while (iterator.hasNext()) {
            TowerEntity towerEntity = iterator.next();
            if (towerEntity != null) {
                if (towerEntity.canUpdate()) {
                    towerEntity.update();
                }
                if (towerEntity.isDead()) {
                    iterator.remove();
                }
            }

        }
    }

    private void addTowerLogic(int x, int y) {
        TowerLogic towerLogic = getTowerRaster(x, y).createLogic(this, x, y);
        if (towerLogic != null) {
            towerLogics.add(towerLogic);
        }
    }

    private void removeTowerLogic(int x, int y) {
        Iterator<TowerLogic> iterator = towerLogics.iterator();
        while (iterator.hasNext()) {
            TowerLogic towerLogic = iterator.next();
            if (towerLogic != null && towerLogic.getX() == x && towerLogic.getY() == y) {
                towerLogic.setInvalid();
                iterator.remove();
                return;
            }
        }
    }
}
