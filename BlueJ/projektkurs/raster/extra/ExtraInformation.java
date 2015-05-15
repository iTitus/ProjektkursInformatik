package projektkurs.raster.extra;

import projektkurs.io.storage.ISaveable;
import projektkurs.io.storage.SaveData;
import projektkurs.lib.ExtraInformationen;
import projektkurs.lib.Strings;
import projektkurs.util.IHasPosition;
import projektkurs.util.IUpdatable;
import projektkurs.world.Spielfeld;

/**
 * Eine ExtraInformation.
 */
public abstract class ExtraInformation implements IUpdatable, ISaveable, IHasPosition<Integer> {

	/**
	 * Spielfeld.
	 */
	protected final Spielfeld map;
	/**
	 * X-Koordinate.
	 */
	protected int posX;
	/**
	 * Y-Koordinate.
	 */
	protected int posY;

	public ExtraInformation(Spielfeld map) {
		this.map = map;
	}

	/**
	 * Konstruktor.
	 *
	 * @param map Spielfeld
	 */
	public ExtraInformation(Spielfeld map, int x, int y) {
		this.map = map;
		posX = x;
		posY = y;
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	/**
	 * Der Interne Name dieses ExtraInformation-Typs.
	 *
	 * @return Interner Name
	 */
	public final String getInternalName() {
		return ExtraInformationen.BACK_MAPPINGS.get(getClass());
	}

	/**
	 * Das Spielfeld.
	 *
	 * @return Spielfeld
	 */
	public Spielfeld getMap() {
		return map;
	}

	@Override
	public Integer getPosX() {
		return posX;
	}

	@Override
	public Integer getPosY() {
		return posY;
	}

	@Override
	public void load(SaveData data) {
		posX = data.getInteger(Strings.EXTRA_X);
		posY = data.getInteger(Strings.EXTRA_Y);
	}

	@Override
	public void setPosition(Integer x, Integer y) {
		posX = x;
		posY = y;
	}

	@Override
	public void update() {
		// NO-OP
	}

	@Override
	public void write(SaveData data) {
		data.set(Strings.EXTRA_X, posX);
		data.set(Strings.EXTRA_Y, posY);
	}

}
