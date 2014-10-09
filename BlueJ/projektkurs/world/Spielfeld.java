package projektkurs.world;

import projektkurs.Main;
import projektkurs.lib.Direction;
import projektkurs.world.raster.AbstractRaster;

public class Spielfeld {

	// erstelle Variablen

	private int[] sichtX;
	private int[] sichtY;
	private int spielerX;
	private int spielerY;

	private int spielfeldLaengeX;
	private int spielfeldLaengeY;
	private boolean[][] zugMoeglich;

	public Spielfeld() {
		spielfeldLaengeX = 100;
		spielfeldLaengeY = 100;

		sichtX = new int[9];
		sichtY = new int[9];
		zugMoeglich = new boolean[spielfeldLaengeX][spielfeldLaengeY];

	}

	// Ihr solltet euch mal Raster.java ansehen!
	// Raster[][] map = new Raster[KartengroesseX][KartengroesseY];
	// ExtraInforamtion[][] extraInfos = new
	// ExtraInformation[KartengroesseX][KartengroesseY];

	// private ExtraInformation[][] = new
	// ExtraInformation[KartengroesseX][KartengroesseY]

	// Waere es nicht besser verschieden grosse Spielfelder zu haben?
	// Also das man bei der Instantiierung die Spielfeldgroessee uebergeben
	// kann?
	// Au�erdem sollte man vielleicht auch die die Bewegungserlaubnis
	// uebergeben,
	// (damit man diese Speichern und Laden kann)
	// wenn man ein neues Spielfeld erstellt. Jedes Feld hat zusaetzlich noch 4
	// oder 8 verschiedene moegliche Bewegungsrichtungen, also muss der Array
	// 3-dimensional sein:

	public Spielfeld(int _sizeX, int _sizeY, boolean[][][] _zugMoeglich) {
		// Magic stuff
	}

	/**
	 * 
	 * @return Aktuelle Kartengroesse in x-Richtung
	 */
	public int getMapSizeX() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 * @return Aktuelle Kartengroesse in y-Richtung
	 */
	public int getMapSizeY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public AbstractRaster getRasterAt(int i, int j) {
		return null;
	}

	/**
	 * Updated alle Objekte und bewgt den Spieler
	 */
	public void update() {

		Direction d = Main.getInputManager().getNextDirection();

		spielerX = spielerX + d.getOffsetX();
		spielerY = spielerY + d.getOffsetY();

		for (int i = 0; i < sichtX.length; i++) {

			// SichtX = SpielerX - 4 + i;

			for (int i2 = 0; i2 < sichtY.length; i2++) {

				// SichtY=SpielerY-4+i2;
			}

		}
	}

	public boolean zugMoeglich() {

		if (zugMoeglich[spielerX][spielerY] == true) {

		} else {

		}

		return false;
	}

	public void zugMoeglichDefinie(boolean[] _zugMoeglich) {

		for (int ix = 0; ix < spielfeldLaengeX; ix++) {

			for (int iy = 0; iy < spielfeldLaengeY; iy++) {

				zugMoeglich[ix][iy] = true;

			}

		}

	}

}
