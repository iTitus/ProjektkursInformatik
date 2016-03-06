package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntitySonnenschirm02 extends Entity {

    public EntitySonnenschirm02(Spielfeld map) {
        super(map);
    }

    public EntitySonnenschirm02(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 2, 2, Sprites.sonnenschirm02);
    }
}
