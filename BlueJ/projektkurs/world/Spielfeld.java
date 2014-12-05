package projektkurs.world;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.io.InputManager;
import projektkurs.raster.AbstractRaster;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.IHasExtraInformation;
import projektkurs.story.StoryManager;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;

/**
 * Ein Spielfeld.
 */
public class Spielfeld implements Cloneable {

    /**
     * Alle Entities.
     */
    private final ArrayList<Entity> entities;
    /**
     * Alle ExtraInformationen.
     */
    private final ArrayList<ExtraInformation> extras;
    /**
     * Alle Raster.
     */
    private final AbstractRaster[][] map;
    /**
     * Spielfeldbreite.
     */
    private final int sizeX;
    /**
     * Spielfeldhöhe.
     */
    private final int sizeY;
    /**
     * X-Koordinate des Spielererscheinungsortes.
     */
    private int spawnX;
    /**
     * Y-Koordinate des Spielererscheinungsortes.
     */
    private int spawnY;
    /**
     * Der Storymanger.
     */
    private final StoryManager storyManager;

    /**
     * Konstrukor.
     *
     * @param sizeX
     *            Spielfeldbreite
     * @param sizeY
     *            Spielfeldhöhe
     * @param spawnX
     *            X-Koordinate des Spielererscheinungsortes
     * @param spawnY
     *            Y-Koordinate des Spielererscheinungsortes
     */
    public Spielfeld(int sizeX, int sizeY, int spawnX, int spawnY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.spawnX = MathUtil.limitArray(spawnX, sizeX);
        this.spawnY = MathUtil.limitArray(spawnY, sizeY);
        map = new AbstractRaster[sizeX][sizeY];
        extras = new ArrayList<ExtraInformation>();
        entities = new ArrayList<Entity>();
        storyManager = new StoryManager();
    }

    /**
     * Kopiert das Spielfeld.
     *
     * @return Spielfeld
     */
    public Spielfeld copy() {
        try {
            return (Spielfeld) clone();
        } catch (Throwable t) {
            Logger.logThrowable("Unable to clone the map", t);
            return null;
        }
    }

    /**
     * Lässt einen Entity verschwinden.
     *
     * @param e
     *            Entity
     */
    public void deSpawn(Entity e) {
        if (e != null) {
            getEntityList().remove(e);
        }
    }

    /**
     * Findet alle Entities in einem gegebenen Rechteck.
     *
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Höhe
     * @return gefundene Entities
     */
    public ArrayList<Entity> getEntitiesInRect(int posX, int posY, int sizeX, int sizeY) {
        ArrayList<Entity> ret = new ArrayList<Entity>();

        for (Entity e : getEntityList()) {
            if (e.isInside(posX, posY, sizeX, sizeY)) {
                ret.add(e);
            }
        }

        return ret;
    }

    /**
     * Findet den Entity an der gegebenen Position.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @return gefundener Entity
     */
    public Entity getEntityAt(int x, int y) {
        if (isInMap(x, y)) {
            for (Entity e : getEntityList()) {
                if (e.getPosX() == x && e.getPosY() == y) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * Alle Entities auf dieser Map.
     *
     * @return alle Entities
     */
    public ArrayList<Entity> getEntityList() {
        return entities;
    }

    /**
     * Findet die ExtraInformation an der gegebenen Position.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @return gefundene ExtraInformation
     */
    public ExtraInformation getExtraInformationAt(int x, int y) {
        if (isInMap(x, y)) {
            for (ExtraInformation extra : getExtraInformationList()) {
                if (extra.getPosX() == x && extra.getPosY() == y) {
                    return extra;
                }
            }
        }
        return null;
    }

    /**
     * Alle ExtraInformationen auf dieser Map.
     *
     * @return alle ExtraInformationen
     */
    public ArrayList<ExtraInformation> getExtraInformationList() {
        return extras;
    }

    /**
     * Breite der Map.
     *
     * @return Breite
     */
    public int getMapSizeX() {
        return sizeX;
    }

    /**
     * Höhe der Map.
     *
     * @return Höhe
     */
    public int getMapSizeY() {
        return sizeY;
    }

    /**
     * Findet das Raster an der gegebenen Position.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @return gefundenes Raster
     */
    public AbstractRaster getRasterAt(int x, int y) {
        if (isInMap(x, y)) {
            return map[x][y];
        }
        return null;
    }

    /**
     * X-Koordinate des Spieler-Erscheinungsortes.
     *
     * @return X-Koordinate
     */
    public int getSpawnX() {
        return spawnX;
    }

    /**
     * Y-Koordinate des Spieler-Erscheinungsortes.
     *
     * @return Y-Koordinate
     */
    public int getSpawnY() {
        return spawnY;
    }

    /**
     * Der StoryManager dieser Map.
     *
     * @return StoryManager
     */
    public StoryManager getStoryManager() {
        return storyManager;
    }

    /**
     * Ist ein Entity an der gegebenen Position.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isEntityAtPos(int x, int y) {
        return getEntityAt(x, y) != null;
    }

    /**
     * Prüft ob die gegebene Koordinate innerhalb dieser Map ist.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @return true, wenn ja; false, wenn nicht
     */
    public boolean isInMap(int x, int y) {
        return MathUtil.isInArray(x, Main.getLevel().getMap().getMapSizeX()) && MathUtil.isInArray(y, Main.getLevel().getMap().getMapSizeY());
    }

    /**
     * Ist ein Raster an der gegebenen Position.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isRasterAt(int x, int y) {
        if (isInMap(x, y)) {
            return map[x][y] != null;
        }
        return false;
    }

    /**
     * Entfernt eine ExtraInformation von dieser Map.
     *
     * @param extra
     *            die zu entfernende ExtraInformation
     */
    public void removeExtraInformation(ExtraInformation extra) {
        getExtraInformationList().remove(extra);
    }

    /**
     * Setzt ein Raster und seine ExtraInformation an die gegebene Position.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @param r
     *            zu setzendes Raster
     */
    public void setRasterAt(int x, int y, AbstractRaster r) {
        if (isInMap(x, y)) {
            ExtraInformation oldExtra = getExtraInformationAt(x, y);
            if (oldExtra != null) {
                removeExtraInformation(oldExtra);
            }
            map[x][y] = r;
            if (r instanceof IHasExtraInformation) {
                ExtraInformation newExtra = ((IHasExtraInformation) r).createExtraInformation();
                newExtra.setPosition(x, y);
                getExtraInformationList().add(newExtra);

            }
        }
    }

    /**
     * Setzt die Koordinaten des Spielererscheinungsortes.
     *
     * @param spawnX
     *            X-Koordinate
     * @param spawnY
     *            Y-Koordinate
     */
    public void setSpawn(int spawnX, int spawnY) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }

    /**
     * Lässt einen Entity erscheinen.
     *
     * @param e
     *            Entity
     */
    public void spawn(Entity e) {
        if (e != null) {
            getEntityList().add(e);
        }
    }

    /**
     * Updated das Spielfeld.
     */
    public void update() {

        Main.getPlayer().moveBy(Main.getInputManager().getNextDirection());

        KeyEvent kE;
        while (Main.getInputManager().hasKeyEvents()) {
            kE = Main.getInputManager().getNextKeyEvent();
            Main.getGui().onKeyTyped(kE.getKeyChar(), kE);
        }

        MouseEvent mE;
        while (Main.getInputManager().hasMouseEvents()) {
            mE = Main.getInputManager().getNextMouseEvent();
            if (mE.getButton() == InputManager.LEFT_MOUSE_BUTTON) {
                Main.getGui().onLeftClick(mE.getX(), mE.getY(), mE);
            }
            if (mE.getButton() == InputManager.RIGHT_MOUSE_BUTTON) {
                Main.getGui().onRightClick(mE.getX(), mE.getY(), mE);
            }
        }

        MouseWheelEvent wE;
        while (Main.getInputManager().hasMouseWheelEvents()) {
            wE = Main.getInputManager().getNextMouseWheelEvent();
            Main.getGui().onMouseWheelMoved(wE.getWheelRotation(), wE);
        }

        for (ExtraInformation extra : getExtraInformationList()) {
            if (extra.canUpdate()) {
                extra.update();
            }
        }

        ArrayList<Entity> toRemove = new ArrayList<Entity>();
        for (Entity e : getEntityList()) {
            if (e != null) {
                if (e.canUpdate()) {
                    e.update();
                }
                if (e.shouldDeSpawn()) {
                    toRemove.add(e);
                }
            }
        }
        getEntityList().removeAll(toRemove);

        storyManager.update();

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
