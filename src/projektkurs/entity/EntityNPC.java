package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.world.Spielfeld;

/**
 * Ein lebender Entity, der kein Spieler ist.
 */
public abstract class EntityNPC extends EntityLiving {

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     */
    public EntityNPC(Spielfeld map) {
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
     * @param image
     *            Bild
     * @param maxHealth
     *            maximale Gesundheit
     */
    public EntityNPC(Spielfeld map, int posX, int posY, int maxHealth, BufferedImage... images) {
        this(map, posX, posY, 1, 1, maxHealth, images);
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
     *            Höhe
     * @param image
     *            Bild
     * @param maxHealth
     *            maximale Gesundheit
     */
    public EntityNPC(Spielfeld map, int posX, int posY, int sizeX, int sizeY, int maxHealth, BufferedImage... images) {
        super(map, posX, posY, sizeX, sizeY, maxHealth, images);
    }

}
