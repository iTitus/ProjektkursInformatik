package projektkurs.io.storage;

public class Feld {

	private final String raster;
	private final int x;
	private final int y;

	public Feld(int x, int y, String raster) {
		this.raster = raster;
		this.x = x;
		this.y = y;
	}

	public String getHintergrund() {
		return raster;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "FeldX=" + x + " FeldY=" + y + " Hintergrund=" + raster;
	}

}
