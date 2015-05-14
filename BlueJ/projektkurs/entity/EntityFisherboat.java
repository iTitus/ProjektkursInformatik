package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityFisherboat extends Entity {

	/**
	 * Konstruktor.
	 *
	 * @param map Spielfeld
	 */
	public EntityFisherboat(Spielfeld map) {
		super(map);
	}

	/**
	 * Konstruktor.
	 *
	 * @param map  Spielfeld
	 * @param posX X-Koordinate
	 * @param posY Y-Koordinate
	 */

	public EntityFisherboat(Spielfeld map, int posX, int posY) {
		super(map, posX, posY, 6, 6, Sprites.fisherboat);
	}

}
