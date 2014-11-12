package projektkurs.world;

import java.util.ArrayList;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityNPC;
import projektkurs.story.Storymanager;
import projektkurs.util.Logger;
import projektkurs.world.raster.AbstractRaster;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.IHasExtraInformation;

/**
 * Spielfeld
 */
public class Spielfeld implements Cloneable {

	private final ArrayList<Entity> entities;

	private final ExtraInformation[][] extras;
	private final AbstractRaster[][] map;

	private final int sizeX, sizeY;

	private final Storymanager storymanager;

	/**
     *
     */
	public Spielfeld(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		map = new AbstractRaster[sizeX][sizeY];
		extras = new ExtraInformation[sizeX][sizeY];
		entities = new ArrayList<Entity>();
		storymanager = new Storymanager();
	}

	public Spielfeld copy() {
		try {
			return (Spielfeld) clone();
		} catch (Throwable t) {
			Logger.logThrowable("Unable to clone the map", t);
			return null;
		}
	}

	/**
	 * @param e
	 */
	public void deSpawn(Entity e) {
		if (e != null) {
			getEntityList().remove(e);
		}
	}

	/**
     *
     */

	public ArrayList<Entity> getEntitiesInRect(int posX, int posY, int sizeX,
			int sizeY) {
		ArrayList<Entity> ret = new ArrayList<Entity>();

		for (Entity e : getEntityList()) {
			if (e.isInside(posX, posY, sizeX, sizeY))
				ret.add(e);
		}

		return ret;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public Entity getEntityAt(int x, int y) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return null;

		for (Entity e : getEntityList()) {
			if (e.getPosX() == x && e.getPosY() == y)
				return e;
		}
		return null;
	}

	/**
     *
     */
	public ArrayList<Entity> getEntityList() {
		return entities;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public ExtraInformation getExtraInformationAt(int x, int y) {
		if (x < 0 || x >= extras.length || y < 0 || y >= extras[x].length)
			return null;
		return extras[x][y];
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public EntityItem getItemAt(int x, int y) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return null;

		for (Entity e : getEntityList()) {
			if (e.getPosX() == x && e.getPosY() == y && e instanceof EntityItem)
				return (EntityItem) e;
		}

		return null;
	}

	/**
	 * @return
	 */
	public int getMapSizeX() {
		return sizeX;
	}

	/**
	 * @return
	 */
	public int getMapSizeY() {
		return sizeY;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public EntityNPC getNPCAt(int x, int y) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return null;

		for (Entity e : getEntityList()) {
			if (e.getPosX() == x && e.getPosY() == y && e instanceof EntityNPC)
				return (EntityNPC) e;
		}

		return null;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public AbstractRaster getRasterAt(int x, int y) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return null;
		return map[x][y];
	}

	public Storymanager getStorymanager() {
		return storymanager;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isEntityAtPos(int x, int y) {
		return getEntityAt(x, y) != null;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isItemAtPos(int x, int y) {
		return getEntityAt(x, y) instanceof EntityItem;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isNPCAtPos(int x, int y) {
		return getEntityAt(x, y) instanceof EntityNPC;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isRasterAt(int x, int y) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return false;
		return map[x][y] != null;
	}

	/**
	 * @param x
	 * @param y
	 * @param r
	 */
	public void setRasterAt(int x, int y, AbstractRaster r) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length) {
			Logger.logThrowable("Failed to set Raster",
					new ArrayIndexOutOfBoundsException(x + " or " + y
							+ " are outside the map's border"));
			return;
		}
		map[x][y] = r;
		if (r instanceof IHasExtraInformation)
			extras[x][y] = ((IHasExtraInformation) r).getExtraInformation(x, y);
	}

	/**
	 * @param e
	 */
	public void spawn(Entity e) {
		if (e != null) {
			getEntityList().add(e);
		}
	}

	/**
	 * Updated das Spielfeld
	 */
	public void update() {

		Main.getPlayer().moveBy(Main.getInputManager().getNextDirection());

		ArrayList<Entity> toRemove = new ArrayList<Entity>();

		for (Entity e : getEntityList()) {
			if (e != null) {
				if (e.canUpdate())
					e.update();
				if (e.shouldDeSpawn())
					toRemove.add(e);
			}
		}
		getEntityList().removeAll(toRemove);

		storymanager.update();

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}