package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityDoor2 extends Entity {
    public EntityDoor2(Spielfeld map) {
        super(map);
    }

    public EntityDoor2(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.door_NS);
    }
}
