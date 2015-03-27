package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityNest extends Entity {

    public EntityNest(Spielfeld map) {
        super(map);
    }

    public EntityNest(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.nest);
    }
}
