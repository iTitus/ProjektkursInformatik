package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityFerry extends Entity {

    public EntityFerry(Spielfeld map) {
        super(map);
    }

    public EntityFerry(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 4, 8, Sprites.ferry);
    }

}
