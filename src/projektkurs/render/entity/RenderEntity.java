package projektkurs.render.entity;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.lib.Integers;

/**
 *
 *
 */
public class RenderEntity {

	private final Entity e;

	public RenderEntity(Entity e) {
		this.e = e;
	}

	public Entity getEntity() {
		return e;
	}

	public int getRelX() {
		return ((e.getPosX() - Main.getRenderHelper().getSightX()) * Integers.RASTER_SIZE)
				+ Integers.WINDOW_HUD_X;
	}

	public int getRelY() {
		return ((e.getPosY() - Main.getRenderHelper().getSightY()) * Integers.RASTER_SIZE)
				+ Integers.WINDOW_HUD_Y;
	}

}
