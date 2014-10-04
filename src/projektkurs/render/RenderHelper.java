package projektkurs.render;

import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;

/**
 * Helperklasse zum Rendern
 * 
 */

@SuppressWarnings("unused")
public class RenderHelper {

	/**
	 * Muss die Map nächsten Tick geupdated
	 */
	private boolean shouldUpdate;
	/**
	 * Sichtfeld
	 */
	private BufferedImage[][] Sight;
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
	 * Gesamte Map
	 */
	private BufferedImage[][] toRender;

	// private int playerPosIn

	/**
	 * Muss nur einmal am Anfang aufgerufen werden, erstellt einen neuen
	 * Renderhelper
	 */
	public RenderHelper() {
		int _MapLengthX = Main.getSpielfeld().getMapSizeX();
		int _MapLengthY = Main.getSpielfeld().getMapSizeY();
		toRender = new BufferedImage[_MapLengthX][_MapLengthY];
		Sight = new BufferedImage[Integers.SIGHT_X][Integers.SIGHT_Y];
		for (int i = 0; i < _MapLengthX; i++) {
			for (int j = 0; j < _MapLengthY; j++) {
				toRender[i][j] = Main.getSpielfeld().getRasterAt(i, j)
						.getImage(i, j);
			}
		}
		setSight(0, 0);
	}

	/**
	 * Returnt die aktuelle Sicht (zB an die Renderklasse)
	 * 
	 * @return aktuelle Sicht
	 */
	public BufferedImage[][] getSight() {
		return Sight;
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

	/**
	 * Aktualisiert das Sichtfeld
	 * 
	 * @param newSightX
	 *            X-Koordinate der oberen linken Ecke des Sichtfeldes
	 * @param newSightY
	 *            Y-Koordinate der oberen linken Ecke des Sichtfeldes
	 */
	public void setSight(int newSightX, int newSightY) {
		sightX = newSightX;
		sightY = newSightY;
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
		for (int x = 0; x < Integers.SIGHT_X; x++) {
			for (int y = 0; y < Integers.SIGHT_Y; y++) {
				if ((x + sightX) < 0
						|| (x + sightX) >= Main.getSpielfeld().getMapSizeX()
						|| (x + sightX) < 0
						|| (y + sightY) >= Main.getSpielfeld().getMapSizeY()
						|| (y + sightY < 0)) {
					Sight[x][y] = Images.baum;
				} else {
					Sight[x][y] = toRender[x + sightX][y + sightY];
				}
			}

			setShouldUpdateNextTick(true);
		}
	}

	/**
	 * Zweite Update-Methode
	 * 
	 * @return int, der die Richtung dokumentiert, in die das Spielfeld
	 *         verschoben wird
	 */
	private int updateSight2() {
		tempMapSizeX = Main.getSpielfeld().getMapSizeX();
		tempMapSizeY = Main.getSpielfeld().getMapSizeY();
		if (sightX < 0) {
			temp |= 0b0001;
			if (sightY < 0) {
				temp |= 0b0100;
				for (int x = 0; x < Integers.SIGHT_X; x++) {
					for (int y = 0; y < Integers.SIGHT_Y; y++) {
						Sight[x][y] = toRender[x][y];
					}
				}
				System.out.println("sightX < 0 ; sightY < 0");
			} else if (sightY + Integers.SIGHT_Y < tempMapSizeY) {
				temp |= 0b1000;
				for (int x = 0; x < Integers.SIGHT_X; x++) {
					for (int y = tempMapSizeY - Integers.SIGHT_Y; y < tempMapSizeY; y++) {
						Sight[x][y - tempMapSizeY + Integers.SIGHT_Y] = toRender[x][y];
					}
				}
				System.out
						.println("sightX < 0 ; sightY + Integers.SIGHT_Y < tempMapSizeY");
			} else {
				for (int x = 0; x < Integers.SIGHT_X; x++) {
					for (int y = sightY; y < sightY + Integers.SIGHT_Y; y++) {
						Sight[x][y - sightY] = toRender[x][y];
					}
				}
				System.out.println("sightX < 0 ; sightY norm");
			}
		} else if (sightX + Integers.SIGHT_X < tempMapSizeX) {
			temp |= 0b0010;
			if (sightY < 0) {
				temp |= 0b0100;
				for (int x = tempMapSizeX - Integers.SIGHT_X; x < tempMapSizeX; x++) {
					for (int y = 0; y < Integers.SIGHT_Y; y++) {
						Sight[x - tempMapSizeX + Integers.SIGHT_X][y] = toRender[x][y];
					}
				}
				System.out
						.println("sightX + Integers.SIGHT_X < tempMapSizeX ; sightY < 0");
			} else if (sightY + Integers.SIGHT_Y < tempMapSizeY) {
				temp |= 0b1000;
				for (int x = tempMapSizeX - Integers.SIGHT_X; x < tempMapSizeX; x++) {
					for (int y = tempMapSizeY - Integers.SIGHT_Y; y < tempMapSizeY; y++) {
						Sight[x - tempMapSizeX + Integers.SIGHT_X][y
								- tempMapSizeY + Integers.SIGHT_Y] = toRender[x][y];
					}
				}
				System.out
						.println("sightX + Integers.SIGHT_X < tempMapSizeX ; sightY + Integers.SIGHT_Y < tempMapSizeY");
			} else {
				for (int x = tempMapSizeX - Integers.SIGHT_X; x < tempMapSizeX; x++) {
					for (int y = sightY; y < sightY + Integers.SIGHT_Y; y++) {
						Sight[x - tempMapSizeX + Integers.SIGHT_X][y - sightY] = toRender[x][y];
					}
				}
				System.out
						.println("sightX + Integers.SIGHT_X < tempMapSizeX ; sightY norm");
			}
		} else {
			if (sightY < 0) {
				temp |= 0b0100;
				for (int x = sightX; x < sightX + Integers.SIGHT_X; x++) {
					for (int y = 0; y < Integers.SIGHT_Y; y++) {
						Sight[x - sightX][y] = toRender[x][y];
					}
				}
				System.out.println("sightx norm ; sightY < 0");
			} else if (sightY + Integers.SIGHT_Y < tempMapSizeY) {
				temp |= 0b1000;
				for (int x = sightX; x < sightX + Integers.SIGHT_X; x++) {
					for (int y = tempMapSizeY - Integers.SIGHT_Y; y < tempMapSizeY; y++) {
						Sight[x - sightX][y - tempMapSizeY + Integers.SIGHT_Y] = toRender[x][y];
					}
				}
				System.out
						.println("sightx norm ; sightY + Integers.SIGHT_Y < tempMapSizeY");
			} else {
				for (int x = sightX; x < sightX + Integers.SIGHT_X; x++) {
					for (int y = sightY; y < sightY + Integers.SIGHT_Y; y++) {
						Sight[x - sightX][y - sightY] = toRender[x][y];
					}
				}
				System.out
						.println("sightx norm ; sightY + Integers.SIGHT_Y < tempMapSizeY");
			}

		}
		setShouldUpdateNextTick(true);
		return temp;
	}

	/**
	 * Dritte Update-Methode
	 * 
	 * @return SCHEIßE
	 */
	private boolean updateSight3() {
		if (!(sightX < Integers.SIGHT_X
				|| sightX > Main.getSpielfeld().getMapSizeX()
						- Integers.SIGHT_X || sightY < Integers.SIGHT_Y || sightY > Main
				.getSpielfeld().getMapSizeY() - Integers.SIGHT_Y)) {

			for (int x = 0; x < Integers.SIGHT_X; x++) {
				for (int y = 0; y < Integers.SIGHT_Y; y++) {
					Sight[x][y] = toRender[x + sightX][y + sightY];
				}
			}

			setShouldUpdateNextTick(true);
			return true;
		} else {
			return false;
		}

	}
}
