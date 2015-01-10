package projektkurs.raster;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import projektkurs.entity.Entity;
import projektkurs.util.Direction;
import projektkurs.util.Logger;

/**
 * Woraus die Welt besteht: Das abstrakte Raster.
 */
public abstract class AbstractRaster {

    /**
     * ID.
     */
    private final int id;
    /**
     * Name.
     */
    private final String name;

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     * @param name
     *            Name
     */
    public AbstractRaster(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Kann ein Entity aus der gegebenen Richtung auf dieses Raster laufen.
     *
     * @param x
     *            X-Koordinate des Rasters
     * @param y
     *            Y-Koordinate des Rasters
     * @param entity
     *            Entity
     * @param dir
     *            Richtung, aus der der Entity kommt
     * @return true, wenn ja; false, wenn nein
     */
    public boolean canWalkOnFromDirection(int x, int y, Entity entity, Direction dir) {
        return true;
    }

    /**
     * ID.
     *
     * @return ID
     */
    public int getID() {
        return id;
    }

    /**
     * Name.
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Wird ausgeführt, wenn ein Entity mit diesem Raster kollidiert - bevor canWalkOnFromDirection ausgeführt wird.
     *
     * @param x
     *            X-Koordinate des Rasters
     * @param y
     *            Y-Koordinate des Rasters
     * @param entity
     *            Entity.
     */
    public void onCollideWith(int x, int y, Entity entity) {
        // NO-OP
    }

    /**
     * Wird ausgeführt, wenn auf dieses Raster mit der linken Maustaster geklickt wird.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @param e
     *            MouseEvent
     */
    public void onLeftClick(int x, int y, MouseEvent e) {
        Logger.info("Left-Clicked on Raster @{x=" + x + ", y=" + y + "}: " + this);
    }

    /**
     * Wird ausgeführt, wenn auf dieses Raster mit der rechten Maustaster geklickt wird.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @param e
     *            MouseEvent
     */
    public void onRightClick(int x, int y, MouseEvent e) {
        Logger.info("Right-Clicked on Raster @{x=" + x + ", y=" + y + "}: " + this);
    }

    /**
     * Wird ausgeführt, wenn ein Entity auf dieses Raster draufläuft.
     *
     * @param x
     *            X-Koordinate des Rasters
     * @param y
     *            Y-Koordinate des Rasters
     * @param entity
     *            Entity
     * @param d
     *            Richtung, aus der der Entity kommt
     */
    public void onWalkOnFromDirection(int x, int y, Entity entity, Direction d) {
        // NO-OP
    }

    /**
     * Rendert das Raster.
     *
     * @param g
     *            Graphics2D
     * @param x
     *            X-Koordinate des Rasters
     * @param y
     *            Y-Koordinate des Rasters
     */
    public abstract void render(Graphics2D g, int x, int y);

    /**
     * Rendert das Raster in CutScenes.
     *
     * @param g
     *            Graphics2D
     * @param x
     *            X-Koordinate des Rasters
     * @param y
     *            Y-Koordinate des Rasters
     */
    public abstract void renderCutScene(Graphics2D g, int x, int y);

    @Override
    public String toString() {
        return "Raster[" + getID() + ", " + getName() + "]";
    }

}
