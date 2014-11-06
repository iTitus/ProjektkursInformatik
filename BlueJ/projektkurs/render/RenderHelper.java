package projektkurs.render;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.render.entity.RenderEntity;

/**
 * Helperklasse zum Rendern
 */
public class RenderHelper {

	/**
     *
     */
	private final Set<RenderEntity> entitiesInSight;

	/**
	 * Sichtfeld
	 */
	private final BufferedImage[][] sight;

	/**
	 * X-Koordinate der oberen linken Ecke des Sichtfeldes in der Map
	 */
	private int sightX;
	/**
	 * Y-Koordinate der oberen linken Ecke des Sichtfeldes in der Map
	 */
	private int sightY;

	/**
	 * Muss nur einmal am Anfang aufgerufen werden, erstellt einen neuen
	 * Renderhelper
	 */
	public RenderHelper() {

		sight = new BufferedImage[Integers.SIGHT_X][Integers.SIGHT_Y];

		sightX = 0;
		sightY = 0;

		entitiesInSight = Collections
				.synchronizedSet(new HashSet<RenderEntity>());

		updateRaster();
		updateEntities();
	}

	public boolean deSpawn(Entity e) {
		if (e != null) {
			synchronized (entitiesInSight) {
				entitiesInSight.remove(new RenderEntity(e));
			}
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public Set<RenderEntity> getEntitiesInSight() {
		synchronized (entitiesInSight) {
			return entitiesInSight;
		}
	}

	/**
	 * @return die aktuelle Sicht
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

	public boolean isInSight(int x, int y) {
		return (x >= sightX && y >= sightY && x < (sightX + Integers.SIGHT_X) && y < (sightY + Integers.SIGHT_Y));
	}

	/**
	 * @param e
	 */
	public void move(Entity e) {
		if (e != null) {
			if (!spawn(e))
				deSpawn(e);
		}
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
		if (dx != 0 || dy != 0) {
			sightX += dx;
			sightY += dy;
			updateRaster();
			updateEntities();
		}
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
		if (sightX != this.sightX || sightY != this.sightY) {
			this.sightX = sightX;
			this.sightY = sightY;
			updateRaster();
			updateEntities();
		}
	}

	public boolean spawn(Entity e) {
		if (e != null
				&& e.isInside(sightX, sightY, Integers.SIGHT_X,
						Integers.SIGHT_Y)) {
			synchronized (entitiesInSight) {
				entitiesInSight.add(new RenderEntity(e));

			}
			return true;
		}
		return false;
	}

	/**
	 * Interne Methode, um die Entities im Sichtfeld zu aktualisieren
	 */
	private void updateEntities() {
		synchronized (entitiesInSight) {
			entitiesInSight.clear();
			for (Entity e : Main.getSpielfeld().getEntitiesInRect(sightX,
					sightY, Integers.SIGHT_X, Integers.SIGHT_Y)) {
				synchronized (entitiesInSight) {
					entitiesInSight.add(new RenderEntity(e));
				}
			}
		}

	}

	/**
	 * Interne Methode, um die Raster im Sichtfeld zu aktualisieren
	 */
	private void updateRaster() {

		for (int x = 0; x < Integers.SIGHT_X; x++) {
			for (int y = 0; y < Integers.SIGHT_Y; y++) {
				if ((x + sightX) < 0
						|| (x + sightX) >= Main.getSpielfeld().getMapSizeX()
						|| (x + sightX) < 0
						|| (y + sightY) >= Main.getSpielfeld().getMapSizeY()
						|| (y + sightY < 0)) {
					sight[x][y] = Images.baum;
				} else {
					if (Main.getSpielfeld().isRasterAt(x + sightX, y + sightY)) {
						sight[x][y] = Main.getSpielfeld()
								.getRasterAt(x + sightX, y + sightY)
								.getImage(x + sightX, y + sightY);
					} else {
						sight[x][y] = null;
					}
				}
			}
		}

	}
}
