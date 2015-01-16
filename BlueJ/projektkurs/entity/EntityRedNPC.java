package projektkurs.entity;

import projektkurs.entity.behaviour.BehaviourRunAround;
import projektkurs.lib.Integers;
import projektkurs.lib.Sprites;
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
     */
    public EntityRedNPC(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, Integers.PLAYER_HEALTH, Sprites.redNPC);
        addBehaviour(new BehaviourRunAround(this));
    }

}
