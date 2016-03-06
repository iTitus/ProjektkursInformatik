package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;


public class EntityMarkise01 extends Entity{

	public EntityMarkise01(Spielfeld map) {
        super(map);
    }

    public EntityMarkise01(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 2, 1, Sprites.markise01);
    }
}
