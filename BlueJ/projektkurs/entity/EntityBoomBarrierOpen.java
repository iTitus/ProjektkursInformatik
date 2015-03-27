package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityBoomBarrierOpen extends Entity {

    public EntityBoomBarrierOpen(Spielfeld map) {
        super(map);
    }

    public EntityBoomBarrierOpen(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 6, 7, Sprites.boomBarrierOpen);
    }

}
