
package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class Entityhouse_2_4x3 extends Entity {

    public Entityhouse_2_4x3(Spielfeld map) {
        super(map);
    }

    public Entityhouse_2_4x3(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 4, 3, Sprites.house_2_4x3);
    }

}
