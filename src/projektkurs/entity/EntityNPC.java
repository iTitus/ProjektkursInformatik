package projektkurs.entity;

import projektkurs.render.Sprite;
import projektkurs.world.Spielfeld;

/**
 * Ein lebender Entity, der kein Spieler ist.
 */
public abstract class EntityNPC extends EntityLiving {

	/**
	 * Konstruktor.
	 *
	 * @param map Spielfeld
	 */
	public EntityNPC(Spielfeld map) {
		super(map);
	}

	/**
	 * Konstruktor.
	 *
	 * @param map       Spielfeld
	 * @param posX      X-Koordinate
	 * @param posY      Y-Koordinate
	 * @param sizeX     Breite
	 * @param sizeY     Hoehe
	 * @param maxHealth maximale Gesundheit
	 * @param sprites   Bilder
	 */
	public EntityNPC(Spielfeld map, int posX, int posY, int sizeX, int sizeY, int maxHealth, Sprite... sprites) {
		super(map, posX, posY, sizeX, sizeY, maxHealth, sprites);
	}

	/**
	 * Konstruktor.
	 *
	 * @param map       Spielfeld
	 * @param posX      X-Koordinate
	 * @param posY      Y-Koordinate
	 * @param maxHealth maximale Gesundheit
	 * @param sprites   Bilder
	 */
	public EntityNPC(Spielfeld map, int posX, int posY, int maxHealth, Sprite... sprites) {
		this(map, posX, posY, 1, 1, maxHealth, sprites);
	}

}
