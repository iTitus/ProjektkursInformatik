package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntitySonnenschirm04 extends Entity {

    public EntitySonnenschirm04(Spielfeld map) {
        super(map);
    }

    public EntitySonnenschirm04(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 2, 2, Sprites.sonnenschirm04);
    }
}
