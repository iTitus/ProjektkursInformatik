package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityMarkise03 extends Entity {

    public EntityMarkise03(Spielfeld map) {
        super(map);
    }

    public EntityMarkise03(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 2, 1, Sprites.markise03);
    }
}
