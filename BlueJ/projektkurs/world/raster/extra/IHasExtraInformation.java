package projektkurs.world.raster.extra;

public interface IHasExtraInformation {

	/**
	 * Gibt die hiermit verbundenen ExtraInformationen zurück. Nur einmal beim
	 * Start aufgerufen.
	 *
	 * @return die ExtraInformation
	 */
	public ExtraInformation getExtraInformation(int x, int y);

}
