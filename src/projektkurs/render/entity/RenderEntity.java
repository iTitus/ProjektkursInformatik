package projektkurs.render.entity;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.lib.Integers;

/**
 * 
 *
 */
public class RenderEntity {

	private Entity e;

	public RenderEntity(Entity e) {
		this.e = e;
	}

	public int getRelX() {
		return e.getPosX()
				- (Main.getRenderHelper().getSightX() * Integers.RASTER_SIZE);
	}

	public int getRelY() {
		return e.getPosY()
				- (Main.getRenderHelper().getSightY() * Integers.RASTER_SIZE);
	}

	public Entity getE() {
		return e;
	}

	@Override
	public int hashCode() {
		return e.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RenderEntity)
			return ((RenderEntity) obj).e.equals(e);
		return super.equals(obj);
	}

}
