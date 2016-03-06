package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntitySonnenschirm03 extends Entity {

    public EntitySonnenschirm03(Spielfeld map) {
        super(map);
    }

    public EntitySonnenschirm03(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 2, 2, Sprites.sonnenschirm03);
    }
}
