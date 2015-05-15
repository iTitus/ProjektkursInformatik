package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityGramophone extends Entity {

    public EntityGramophone(Spielfeld map) {
        super(map);
    }

    public EntityGramophone(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.gramophoneEntity);
    }

}
