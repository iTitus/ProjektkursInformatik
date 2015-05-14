package projektkurs.raster;

import java.awt.event.MouseEvent;
import java.util.List;

import projektkurs.entity.Entity;
import projektkurs.render.Screen;
import projektkurs.util.Direction;
import projektkurs.util.I18n;
import projektkurs.util.IWorldTooltipProvider;
import projektkurs.util.Logger;
import projektkurs.world.Spielfeld;

/**
 * Woraus die Welt besteht: Das abstrakte Raster.
 */
public abstract class AbstractRaster implements IWorldTooltipProvider {

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
	 * @param id   ID
	 * @param name Name
	 */
	public AbstractRaster(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public void addTooltip(Spielfeld map, int x, int y, List<String> tooltip) {
		tooltip.add(getName());
	}

	/**
	 * Kann ein Entity aus der gegebenen Richtung auf dieses Raster laufen.
	 *
	 * @param map    Spielfeld
	 * @param x      X-Koordinate des Rasters
	 * @param y      Y-Koordinate des Rasters
	 * @param entity Entity
	 * @param dir    Richtung, aus der der Entity kommt
	 * @return true, wenn ja; false, wenn nein
	 */
	public boolean canWalkOnFromDirection(Spielfeld map, int x, int y, Entity entity, Direction dir) {
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
		return I18n.getString("raster." + name + ".name");
	}

	/**
	 * Wird ausgefuehrt, wenn ein Entity mit diesem Raster kollidiert - bevor canWalkOnFromDirection ausgefuehrt wird.
	 *
	 * @param map    Spielfeld
	 * @param x      X-Koordinate des Rasters
	 * @param y      Y-Koordinate des Rasters
	 * @param entity Entity.
	 */
	public void onCollideWith(Spielfeld map, int x, int y, Entity entity) {
		// NO-OP
	}

	/**
	 * Wird ausgefuehrt, wenn auf dieses Raster mit der linken Maustaste geklickt wird.
	 *
	 * @param map Spielfeld
	 * @param x   X-Koordinate
	 * @param y   Y-Koordinate
	 * @param e   MouseEvent
	 */
	public void onLeftClick(Spielfeld map, int x, int y, MouseEvent e) {
		Logger.info("Left-Clicked on Raster @{x=" + x + ", y=" + y + "}: " + this);
	}

	/**
	 * Wird ausgefuehrt, wenn auf dieses Raster mit der rechten Maustaste geklickt wird.
	 *
	 * @param map Spielfeld
	 * @param x   X-Koordinate
	 * @param y   Y-Koordinate
	 * @param e   MouseEvent
	 */
	public void onRightClick(Spielfeld map, int x, int y, MouseEvent e) {
		Logger.info("Right-Clicked on Raster @{x=" + x + ", y=" + y + "}: " + this);
	}

	/**
	 * Wird ausgefuehrt, wenn ein Entity auf dieses Raster drauflaeuft.
	 *
	 * @param map    Spielfeld
	 * @param x      X-Koordinate des Rasters
	 * @param y      Y-Koordinate des Rasters
	 * @param entity Entity
	 * @param d      Richtung, aus der der Entity kommt
	 */
	public void onWalkOnFromDirection(Spielfeld map, int x, int y, Entity entity, Direction d) {
		// NO-OP
	}

	/**
	 * Rendert das Raster.
	 *
	 * @param screen Screen
	 * @param map    Spielfeld
	 * @param x      X-Koordinate des Rasters
	 * @param y      Y-Koordinate des Rasters
	 */
	public abstract void render(Screen screen, Spielfeld map, int x, int y);

	/**
	 * Rendert das Raster in CutScenes.
	 *
	 * @param screen Screen
	 * @param x      X-Koordinate des Rasters
	 * @param y      Y-Koordinate des Rasters
	 */
	public abstract void renderCutScene(Screen screen, int x, int y);

	@Override
	public String toString() {
		return "Raster[" + id + ", " + name + "]";
	}
}
