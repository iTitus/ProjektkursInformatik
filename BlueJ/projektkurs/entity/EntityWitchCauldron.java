package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityWitchCauldron extends Entity {

    public EntityWitchCauldron(Spielfeld map) {
        super(map);
    }

    public EntityWitchCauldron(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 6, 7, Sprites.witchCauldron);
    }

}
