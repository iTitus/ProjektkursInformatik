package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityMarkise02 extends Entity {

    public EntityMarkise02(Spielfeld map) {
        super(map);
    }

    public EntityMarkise02(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 2, 1, Sprites.markise02);
    }
}
