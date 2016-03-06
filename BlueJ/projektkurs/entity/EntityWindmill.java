package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityWindmill extends Entity {

    public EntityWindmill(Spielfeld map) {
        super(map);
    }

    public EntityWindmill(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 4, 4, Sprites.windmill);
    }
}
