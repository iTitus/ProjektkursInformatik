package projektkurs.entity;

import java.awt.image.BufferedImage;

/**
 * Ein lebender Entity, der kein Spieler ist.
 */
public abstract class EntityNPC extends EntityLiving {

    /**
     * Konstruktor.
     */
    public EntityNPC() {
        super();
    }

    /**
     * Konstruktor.
     *
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param image
     *            Bild
     * @param maxHealth
     *            maximale Gesundheit
     */
    public EntityNPC(int posX, int posY, int maxHealth, BufferedImage... images) {
        this(posX, posY, 1, 1, maxHealth, images);
    }

    /**
     * Konstruktor.
     *
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
    public EntityNPC(int posX, int posY, int sizeX, int sizeY, int maxHealth, BufferedImage... images) {
        super(posX, posY, sizeX, sizeY, maxHealth, images);
    }

}
