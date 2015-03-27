package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityHexenkessel extends Entity {

    public EntityHexenkessel(Spielfeld map) {
        super(map);
    }

    public EntityHexenkessel(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 6, 7, Sprites.hexenkessel);
    }

}
