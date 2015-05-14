package projektkurs.util;

/**
 * Interface fuer Objekte, die eine Position und eine Groesse haben.
 */
public interface IHasPositionAndSize<P extends Number, S extends Number> extends IHasPosition<P> {

	/**
	 * Die Breite.
	 *
	 * @return Breite
	 */
	S getSizeX();

	/**
	 * Die Hoehe.
	 *
	 * @return Hoehe
	 */
	S getSizeY();

	/**
	 * Setzt die Groesse.
	 *
	 * @param sizeX Breite
	 * @param sizeY Hoehe
	 */
	void setSize(S sizeX, S sizeY);

}
