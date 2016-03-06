
package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class Entityhouse_4b_3x4 extends Entity {

    public Entityhouse_4b_3x4(Spielfeld map) {
        super(map);
    }

    public Entityhouse_4b_3x4(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 3, 4, Sprites.house_4b_3x4);
    }

}
