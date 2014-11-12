package projektkurs.world.raster.extra;

/**
 * Interface für Raster, die eine ExtraInformation besitzen. Das ist
 * grammatikalisch richtig!
 */
public interface IHasExtraInformation {

	/**
	 * Gibt die mit dem Raster verbundene ExtraInformation zurück.
	 * 
	 * @param x
	 * @param y
	 * @return die ExtraInformation
	 */
	public ExtraInformation getExtraInformation(int x, int y);

}
