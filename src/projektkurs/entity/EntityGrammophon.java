package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityGrammophon extends Entity{
	
	public EntityGrammophon(Spielfeld map) {
        super(map);
    }

	public EntityGrammophon(Spielfeld map, int posX, int posY) {
        super(map, posX, posY, 1, 1, Sprites.grammophon);
    }

    
}