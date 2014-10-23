package projektkurs.render;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.render.entity.RenderEntity;

/**
 * Helperklasse zum Rendern
 * 
 */
@SuppressWarnings("unused")
public class RenderHelper {

	/**
	 * 
	 */
	private Collection<RenderEntity> entitiesInSight;
	private boolean shouldUpdateEntities;

	private boolean shouldUpdateRaster;

	/**
	 * Sichtfeld
	 */
	private BufferedImage[][] sight;

	/**
	 * X-Koordinate der oberen linken Ecke des Sichtfeldes in der Map
	 */
	private int sightX;
	/**
	 * Y-Koordinate der oberen linken Ecke des Sichtfeldes in der Map
	 */
	private int sightY;

	/**
	 * Temporäre Kartengröße in x-Richtung
	 */
	private int tempMapSizeX;

	/**
	 * Temporäre Kartengröße in y-Richtung
	 */
	private int tempMapSizeY;

	/**
	 * Gesamte Map<br>
	 * <br>
	 * 1D: X-Koordinate<br>
	 * 2D: Y-Koordinate<br>
	 * 
	 */
	private BufferedImage[][] toRender;

	/**
	 * Muss nur einmal am Anfang aufgerufen werden, erstellt einen neuen
	 * Renderhelper
	 */
	public RenderHelper() {
		int _MapLengthX = Main.getSpielfeld().getMapSizeX();
		int _MapLengthY = Main.getSpielfeld().getMapSizeY();

		toRender = new BufferedImage[_MapLengthX][_MapLengthY];

		sight = new BufferedImage[Integers.SIGHT_X][Integers.SIGHT_Y];

		sightX = 0;
		sightY = 0;

		shouldUpdateEntities = true;
		shouldUpdateRaster = true;

		entitiesInSight = Collections
				.synchronizedCollection(new ArrayList<RenderEntity>());

		for (int i = 0; i < _MapLengthX; i++) {
			for (int j = 0; j < _MapLengthY; j++) {
				toRender[i][j] = Main.getSpielfeld().getRasterAt(i, j)
						.getImage(i, j);
			}
		}
		setSight(0, 0);
	}

	/**
	 * 
	 * @return
	 */
	public Collection<RenderEntity> getEntitiesInSight() {
		synchronized (entitiesInSight) {
			return entitiesInSight;
		}
	}

	/**
	 * Returnt die aktuelle Sicht (zB an die Renderklasse)
	 * 
	 * @return aktuelle Sicht
	 */
	public BufferedImage[][] getSight() {
		return sight;
	}

	/**
	 * X-Koordinate der oberen linken Ecke des Sichtfeldes in der Map
	 * 
	 * @return
	 */
	public int getSightX() {
		return sightX;
	}

	/**
	 * Y-Koordinate der oberen linken Ecke des Sichtfeldes in der Map
	 * 
	 * @return
	 */
	public int getSightY() {
		return sightY;
	}

	/**
	 * Bewegt das Spielfeld um dx und dy
	 * 
	 * @param dx
	 *            Bewegung in x-Richtung
	 * @param dy
	 *            Bewegung in y-Richtung
	 */
	public void moveSight(int dx, int dy) {
		sightX += dx;
		sightY += dy;
		if (!(dx == 0 && dy == 0)) {
			updateRaster();
			updateEntities();
		}
	}

	public void removeRenderEntity(Entity e) {
		synchronized (entitiesInSight) {
			entitiesInSight.remove(new RenderEntity(e));
		}
		updateEntities();
	}

	public void setShouldUpdateEntities(boolean shouldUpdateEntities) {
		this.shouldUpdateEntities = shouldUpdateEntities;
	}

	public void setShouldUpdateRaster(boolean shouldUpdateRaster) {
		this.shouldUpdateRaster = shouldUpdateRaster;
	}

	/**
	 * Aktualisiert das Sichtfeld
	 * 
	 * @param newSightX
	 *            X-Koordinate der oberen linken Ecke des Sichtfeldes
	 * @param newSightY
	 *            Y-Koordinate der oberen linken Ecke des Sichtfeldes
	 */
	public void setSight(int sightX, int sightY) {
		this.sightX = sightX;
		this.sightY = sightY;
		updateRaster();
		updateEntities();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param bImage
	 */
	public void setToRenderRasters(int x, int y, BufferedImage bImage) {
		toRender[x][y] = bImage;
		updateRaster();
	}

	public boolean shouldUpdateEntities() {
		return shouldUpdateEntities;
	}

	public boolean shouldUpdateRaster() {
		return shouldUpdateRaster;
	}

	/**
	 * Ermoeglicht die Veraenderung der Texturen von Rastern
	 * 
	 * @param xCoordinate
	 *            X-Koordinate des zu aendernden Rasters
	 * @param yCoordinate
	 *            Y-Koordinate des zu aendernden Rasters
	 * @param img
	 *            Bild neu dahin gesetzt werden soll
	 */
	public void updateRender(int x, int y) {
		toRender[x][y] = Main.getSpielfeld().getRasterAt(x, y).getImage(x, y);
		updateRaster();

	}

	private void updateEntities() {
		if (shouldUpdateEntities) {
			synchronized (entitiesInSight) {
				entitiesInSight.clear();
				for (Entity e : Main.getSpielfeld().getEntitiesInRec(sightX,
						sightY, Integers.SIGHT_X * Integers.RASTER_SIZE,
						Integers.SIGHT_Y * Integers.RASTER_SIZE))
					entitiesInSight.add(new RenderEntity(e));
			}
		}

	}

	/**
	 * Interne Methode, um das Sichtfeld zu aktualisieren
	 */
	private void updateRaster() {
		if (shouldUpdateRaster)
			for (int x = 0; x < Integers.SIGHT_X; x++) {
				for (int y = 0; y < Integers.SIGHT_Y; y++) {
					if ((x + sightX) < 0
							|| (x + sightX) >= Main.getSpielfeld()
									.getMapSizeX()
							|| (x + sightX) < 0
							|| (y + sightY) >= Main.getSpielfeld()
									.getMapSizeY() || (y + sightY < 0)) {
						sight[x][y] = Images.baum;
					} else {
						sight[x][y] = toRender[x + sightX][y + sightY];
					}
				}
			}
	}
}
