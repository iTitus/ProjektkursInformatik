package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.entity.behaviour.BehaviourRunAround;
import projektkurs.lib.Integers;
import projektkurs.world.Spielfeld;

/**
 * Der rote NPC.
 */
public class EntityRedNPC extends EntityNPC {

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     */
    public EntityRedNPC(Spielfeld map) {
        super(map);
        addBehaviour(new BehaviourRunAround(this));
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
     */
    public EntityRedNPC(Spielfeld map, int posX, int posY, BufferedImage image) {
        super(map, posX, posY, Integers.PLAYER_HEALTH, image);
        addBehaviour(new BehaviourRunAround(this));
    }

}
