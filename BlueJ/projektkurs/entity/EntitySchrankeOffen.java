package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntitySchrankeOffen extends Entity {

    public EntitySchrankeOffen(Spielfeld map) {
        super(map);
    }

    public EntitySchrankeOffen(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 6, 7, Sprites.schrankeOffen);
    }

}
