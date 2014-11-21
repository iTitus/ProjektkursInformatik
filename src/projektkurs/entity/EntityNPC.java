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
    public EntityNPC(int posX, int posY, BufferedImage image, int maxHealth) {
        this(posX, posY, 1, 1, image, maxHealth);
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
    public EntityNPC(int posX, int posY, int sizeX, int sizeY, BufferedImage image, int maxHealth) {
        super(posX, posY, sizeX, sizeY, image, maxHealth);
    }

}
