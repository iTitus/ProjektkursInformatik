package projektkurs.entity;

import projektkurs.dialog.ITalkable;
import projektkurs.render.Sprite;
import projektkurs.world.Spielfeld;

/**
 * Ein Entity, mit dem man einem Dialog beginnen kann.
 */
public abstract class EntityDialogNPC extends EntityNPC implements ITalkable {

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     */
    public EntityDialogNPC(Spielfeld map) {
        super(map);
    }

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Hoehe
     * @param maxHealth
     *            maximale Gesundheit
     * @param sprites
     *            Bilder
     */
    public EntityDialogNPC(Spielfeld map, int posX, int posY, int sizeX, int sizeY, int maxHealth, Sprite... sprites) {
        super(map, posX, posY, sizeX, sizeY, maxHealth, sprites);
    }

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param maxHealth
     *            maximale Gesundheit
     * @param sprites
     *            Bilder
     */
    public EntityDialogNPC(Spielfeld map, int posX, int posY, int maxHealth, Sprite... sprites) {
        this(map, posX, posY, 1, 1, maxHealth, sprites);
    }

    @Override
    public boolean shouldStartDialog() {
        return true;
    }

}
