package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityGrammophonganz extends Entity {

    public EntityGrammophonganz(Spielfeld map) {
        super(map);
    }

    public EntityGrammophonganz(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.gramophoneItem);
    }

}
