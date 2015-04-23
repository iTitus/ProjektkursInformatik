package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityFerryman extends Entity {

    public EntityFerryman(Spielfeld map) {
        super(map);
    }

    public EntityFerryman(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.boy);
    }

}
