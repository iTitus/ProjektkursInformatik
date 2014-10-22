package projektkurs.render;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;

/**
 * Helperklasse zum Rendern
 * 
 */

@SuppressWarnings("all")
public class RenderHelper {

	/**
	 * 
	 */
	private Collection<Entity> entitiesInSight;

	/**
	 * Muss die Map nächsten Tick geupdated
	 */
	private boolean shouldUpdate;
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
	 * Temporäre Variable
	 */
	private int temp;

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

		entitiesInSight = Collections
				.synchronizedCollection(new Vector<Entity>());

		for (int i = 0; i < _MapLengthX; i++) {
			for (int j = 0; j < _MapLengthY; j++) {
				toRender[i][j] = Main.getSpielfeld().getRasterAt(i, j)
						.getImage(i, j);
				// if (Main.getSpielfeld().getItemAt(i, j) != null
				// && Main.getSpielfeld().getItemAt(i, j).getImage() != null)
				// toRenderItems[i][j] = Main.getSpielfeld().getItemAt(i, j)
				// .getImage();
				// if (Main.getSpielfeld().getNPCAt(i, j) != null
				// && Main.getSpielfeld().getNPCAt(i, j)
				// .getBufferedImage() != null)
				// toRenderNPCs[i][j] = Main.getSpielfeld().getNPCAt(i, j)
				// .getBufferedImage();
			}
		}
		setSight(0, 0);
	}

	// public BufferedImage[][] getSightItems() {
	// return SightItems;
	// }
	//
	// public BufferedImage[][] getSightNPCs() {
	// return SightNPCs;
	// }

	/**
	 * 
	 * @return
	 */
	public Collection<Entity> getEntitiesInSight() {
		synchronized (entitiesInSight) {
			return Collections.unmodifiableCollection(entitiesInSight);
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
		if (!(dx == 0 && dy == 0))
			updateSight();
	}

	/**
	 * Setzt das shouldUpdate-Feldd
	 * 
	 * @param update
	 *            ob der Bildschirm nächsten Tick aktualisiert werden soll
	 */
	public void setShouldUpdateNextTick(boolean update) {
		shouldUpdate = update;
	}

	// public void setToRenderItems(int x, int y, BufferedImage bImage) {
	// toRenderItems[x][y] = bImage;
	// updateSight();
	// }
	//
	// public void setToRenderNPCs(int x, int y, BufferedImage bImage) {
	// toRenderNPCs[x][y] = bImage;
	// updateSight();
	// }

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
		updateSight();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param bImage
	 */
	public void setToRenderRasters(int x, int y, BufferedImage bImage) {
		toRender[x][y] = bImage;
		updateSight();
	}

	/**
	 * Ob es notwendig ist, nächsten Tick den Bildschirm zu aktualisieren
	 * 
	 * @return true, wenn es notwendig ist
	 */
	public boolean shouldUpdate() {
		return shouldUpdate;
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
		updateSight();

	}

	/**
	 * Interne Methode, um das Sichtfeld zu aktualisieren
	 */
	private void updateSight() {

		// FIXME: Buggt rum bei anderen Fenstergrößen

		for (int x = 0; x < Integers.SIGHT_X; x++) {
			for (int y = 0; y < Integers.SIGHT_Y; y++) {
				if ((x + sightX) < 0
						|| (x + sightX) >= Main.getSpielfeld().getMapSizeX()
						|| (x + sightX) < 0
						|| (y + sightY) >= Main.getSpielfeld().getMapSizeY()
						|| (y + sightY < 0)) {
					sight[x][y] = Images.baum;
					// SightItems[x][y] = null;
					// SightNPCs = null;
				} else {
					sight[x][y] = toRender[x + sightX][y + sightY];
					// SightItems[x][y] = toRenderItems[x + sightX][y + sightY];
					// SightNPCs[x][y] = toRenderNPCs[x + sightX][y + sightY];
				}
			}

			setShouldUpdateNextTick(true);
		}
	}

}
