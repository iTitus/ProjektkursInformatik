package projektkurs.world.raster;

import java.awt.Graphics2D;

import projektkurs.entity.Entity;
import projektkurs.util.Direction;
import projektkurs.util.Logger;

/**
 * Woraus die Welt besteht: Das abstrakte Raster
 */
public abstract class AbstractRaster {

	/**
	 * Kann eine Figur aus der gegebenen Richtung auf dieses Raster laufen?
	 *
	 * @param x
	 *            x-Koordinate des Rasters
	 * @param y
	 *            xy-Koordinate des Rasters
	 * @param dir
	 *            Richtung, aus der das 'Etwas' kommt
	 * @return true, wenn ja
	 */
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {
		return true;
	}

	/**
	 * @param entity
	 */
	public void onCollideWith(int x, int y, Entity entity) {
		// NO-OP
	}

	/**
	 * Linksklick
	 * 
	 * @param x
	 * @param y
	 */
	public void onLeftClick(int x, int y) {
		Logger.info("Left-Clicked on Raster @{x=" + x + ", y=" + y + "}: "
				+ this);
	}

	/**
	 * Rechtsklick
	 * 
	 * @param x
	 * @param y
	 */
	public void onRightClick(int x, int y) {
		Logger.info("Right-Clicked on Raster @{x=" + x + ", y=" + y + "}: "
				+ this);
	}

	/**
	 * @param x
	 * @param y
	 * @param entity
	 * @param d
	 */
	public void onWalkOnFromDirection(int x, int y, Entity entity, Direction d) {
		// NO-OP
	}

	/**
	 * Rendert das Raster
	 * 
	 * @param g
	 * @param x
	 * @param y
	 */
	public abstract void render(Graphics2D g, int x, int y);

	/**
	 * Rendert das Raster in CutScenes
	 * 
	 * @param g
	 * @param x
	 * @param y
	 */
	public abstract void renderCutScene(Graphics2D g, int mapX, int mapY);

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
