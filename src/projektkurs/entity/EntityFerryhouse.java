package projektkurs.entity;

import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

public class EntityFerryhouse extends Entity {

	public EntityFerryhouse(Spielfeld map) {
		super(map);
	}

	public EntityFerryhouse(Spielfeld map, int posX, int posY) {
		super(map, posX, posY, 6, 7, Sprites.ferryhouse);
	}

}
