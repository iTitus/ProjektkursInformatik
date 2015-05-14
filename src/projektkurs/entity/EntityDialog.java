package projektkurs.entity;

import projektkurs.dialog.ITalkable;
import projektkurs.render.Sprite;
import projektkurs.world.Spielfeld;

/**
 * Ein Objekt, mit dem reden kann.
 */
public abstract class EntityDialog extends Entity implements ITalkable {

	/**
	 * Konstruktor.
	 *
	 * @param map Spielfeld
	 */
	public EntityDialog(Spielfeld map) {
		super(map);
	}

	/**
	 * Konstruktor.
	 *
	 * @param map     Spielfeld
	 * @param posX    X-Position
	 * @param posY    Y-Position
	 * @param sizeX   Breite
	 * @param sizeY   Hoehe
	 * @param sprites Sprite
	 */
	public EntityDialog(Spielfeld map, int posX, int posY, int sizeX, int sizeY, Sprite... sprites) {
		super(map, posX, posY, sizeX, sizeY, sprites);
	}

	/**
	 * Konstruktor.
	 *
	 * @param map     Spielfeld
	 * @param posX    X-Position
	 * @param posY    Y-Position
	 * @param sprites Sprite
	 */
	public EntityDialog(Spielfeld map, int posX, int posY, Sprite... sprites) {
		this(map, posX, posY, 1, 1, sprites);
	}

	@Override
	public boolean shouldStartDialog() {
		return true;
	}

}
