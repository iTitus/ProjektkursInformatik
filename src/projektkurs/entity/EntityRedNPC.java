package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.entity.behaviour.BehaviourRunAround;
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
        addBehaviour(new BehaviourRunAround(this));
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

}
