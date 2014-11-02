package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.entity.Entity;
import projektkurs.util.Direction;
import projektkurs.util.Logger;
import projektkurs.world.raster.extra.ExtraInformation;

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
	 * Gibt die hiermit verbundenen ExtraInformationen zurueck. Nur einmal beim
	 * Start aufgerufen.
	 * 
	 * @return die ExtraInformation
	 */
	public ExtraInformation getExtraInformation() {
		return null;
	}

	/**
	 * Gibt die Textur zurueck
	 * 
	 * @param x
	 *            x-Koordinate des Rasters
	 * @param y
	 *            y-Koordinate des Rasters
	 * @return die Textur
	 */
	public abstract BufferedImage getImage(int x, int y);

	/**
	 * Ist dieses Raster interaktiv?
	 * 
	 * @return true, wenn ja
	 */
	public boolean isInteractive() {
		return false;
	}

	/**
	 * Wenn mit der Maus auf das Raster geklickt wird
	 * 
	 * @param x
	 *            x-Koordinate des Rasters
	 * @param y
	 *            y-Koordinate des Rasters
	 * @param button
	 *            Der Mausknopf
	 */
	public void onClick(int x, int y, int button) {
		Logger.info("Clicked on Raster @{x=" + x + ", y=" + y + "}: "
				+ this.toString());
	}

	/**
	 * 
	 * @param entity
	 */
	public void onCollideWith(int x, int y, Entity entity) {
		// NO-OP
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param entity
	 * @param d
	 */
	public void onWalkFromDirection(int x, int y, Entity entity, Direction d) {
		// NO-OP
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
