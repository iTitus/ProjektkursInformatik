package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;


public class EntitySonnenschirm01 extends Entity{

	public EntitySonnenschirm01(Spielfeld map) {
        super(map);
    }

    public EntitySonnenschirm01(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 2, 2, Sprites.sonnenschirm01);
    }
}
