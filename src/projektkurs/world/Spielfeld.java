package projektkurs.world;

import java.util.ArrayList;
import java.util.Random;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityNPC;
import projektkurs.entity.EntityRedNPC;
import projektkurs.item.ItemStack;
import projektkurs.item.Items;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.story.scripts.Scripts;
import projektkurs.story.trigger.InventoryTrigger;
import projektkurs.util.Direction;
import projektkurs.util.Logger;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.raster.AbstractRaster;
import projektkurs.world.raster.Raster;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationDoor;
import projektkurs.world.raster.extra.ExtraInformationKiste;
import projektkurs.world.raster.extra.IHasExtraInformation;

/**
 * Spielfeld
 */
public class Spielfeld implements Cloneable {

	private static final int MAP_SIZE_X = Integers.SIGHT_X * 2;
	private static final int MAP_SIZE_Y = Integers.SIGHT_Y * 2;
	private static final Random rand = new Random();

	private final ArrayList<Entity> entities;

	private final ExtraInformation[][] extras;

	private final AbstractRaster[][] map;

	/**
     *
     */
	public Spielfeld() {
		map = new AbstractRaster[MAP_SIZE_X][MAP_SIZE_Y];
		extras = new ExtraInformation[MAP_SIZE_X][MAP_SIZE_Y];
		entities = new ArrayList<Entity>();
		generateAndPopulateMap();
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
	public void generateAndPopulateMap() {
		// RASEN!
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++)
				setRasterAt(x, y, Raster.rasen);

		}

		// BÄUME!
		for (int i = 0; i < rand.nextInt(51) + 25; i++) {
			setRasterAt(rand.nextInt(MAP_SIZE_X), rand.nextInt(MAP_SIZE_Y),
					Raster.baum);
		}

		// KISTEN!
		for (int i = 0; i < rand.nextInt(7) + 3; i++) {
			setRasterAt(rand.nextInt(MAP_SIZE_X), rand.nextInt(MAP_SIZE_Y),
					Raster.kiste);

		}

		// WÄNDE!
		for (int x = 0; x < map.length; x++) {
			setRasterAt(x, 0, Raster.wand);
			setRasterAt(x, MAP_SIZE_Y - 1, Raster.wand);
		}
		for (int y = 0; y < map.length; y++) {
			setRasterAt(0, y, Raster.wand);
			setRasterAt(MAP_SIZE_X - 1, y, Raster.wand);
		}

		// Animation Test
		setRasterAt(1, 1, Raster.testAnimation);

		// TÜREN!
		setRasterAt(20, 18, Raster.baum);
		setRasterAt(21, 18, Raster.baum);
		setRasterAt(22, 18, Raster.baum);
		setRasterAt(23, 18, Raster.baum);
		setRasterAt(24, 18, Raster.baum);
		setRasterAt(24, 19, Raster.baum);
		setRasterAt(24, 20, Raster.baum);
		setRasterAt(24, 21, Raster.baum);
		setRasterAt(24, 22, Raster.baum);
		setRasterAt(20, 19, Raster.baum);
		setRasterAt(20, 20, Raster.door);
		setRasterAt(20, 21, Raster.baum);
		setRasterAt(20, 22, Raster.baum);
		setRasterAt(21, 22, Raster.baum);
		setRasterAt(22, 22, Raster.baum);
		setRasterAt(23, 22, Raster.baum);
		setRasterAt(22, 20, Raster.finish);
		((ExtraInformationDoor) getExtraInformationAt(20, 20))
				.setDirection(Direction.LEFT);
		((ExtraInformationDoor) getExtraInformationAt(20, 20))
				.setOpeningKey(1000);

		// KISTENINHALTE!
		for (int x = 0; x < extras.length; x++) {
			for (int y = 0; y < extras[x].length; y++) {
				if (getExtraInformationAt(x, y) instanceof ExtraInformationKiste) {
					((ExtraInformationKiste) getExtraInformationAt(x, y))
							.getInventar().addItemStack(
									new ItemStack(Items.item_42, 42));
					((ExtraInformationKiste) getExtraInformationAt(x, y))
							.getInventar().addItemStack(
									new ItemStack(Items.nuke));
					((ExtraInformationKiste) getExtraInformationAt(x, y))
							.getInventar().addItemStack(
									new ItemStack(Items.key));
				}
			}
		}

		// ENTITIES!
		spawn(Main.getPlayer());
		spawn(new EntityRedNPC(1, 1, Images.redNPC));
		spawn(new EntityItem(5, 5, new ItemStack(Items.key, 1, 1000)));
		spawn(new EntityItem(5, 6, new ItemStack(Items.item_42, 42)));
		spawn(new EntityItem(5, 7, new ItemStack(Items.nuke)));

		// STORYMANAGER!
		Main.getStoryManager().addTrigger(
				new InventoryTrigger(ReflectionUtil.getMethod(Scripts.class,
						"loose"), new ItemStack(Items.nuke)));

	}

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
		return MAP_SIZE_X;
	}

	/**
	 * @return
	 */
	public int getMapSizeY() {
		return MAP_SIZE_Y;
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
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return;
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

		Main.getStoryManager().update();

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}