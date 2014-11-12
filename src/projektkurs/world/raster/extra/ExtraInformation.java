package projektkurs.world.raster.extra;

import projektkurs.util.ICanUpdate;

/**
 * Tolle ExtraInformationen
 */
public class ExtraInformation implements ICanUpdate {

	protected final int x, y;

	/**
	 * Konstruktor
	 */
	public ExtraInformation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void update() {
		// NO-OP
	}

}
