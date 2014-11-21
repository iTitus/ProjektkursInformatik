package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.entity.behaviour.Behaviours;
import projektkurs.lib.Integers;

/**
 * Der rote NPC.
 */
public class EntityRedNPC extends EntityNPC {

    /**
     * Konstruktor.
     */
    public EntityRedNPC() {
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
     */
    public EntityRedNPC(int posX, int posY, BufferedImage image) {
        super(posX, posY, image, Integers.PLAYER_HEALTH);
    }

    @Override
    public Behaviours getBehaviour() {
        return Behaviours.RUN_AROUND;
    }

}
