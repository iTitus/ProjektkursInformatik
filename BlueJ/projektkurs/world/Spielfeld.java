package projektkurs.world;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityNPC;
import projektkurs.entity.EntityRedNPC;
import projektkurs.item.ItemStack;
import projektkurs.item.Items;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.util.Direction;
import projektkurs.world.raster.AbstractRaster;
import projektkurs.world.raster.Raster;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationKiste;

/**
 * Spielfeld
 * 
 */
public class Spielfeld {

	private int ups;
	private int staticUPS;
	private long lastUPSMeasure;

	private static final int MAP_SIZE_X = Integers.SIGHT_X * 2;
	private static final int MAP_SIZE_Y = Integers.SIGHT_Y * 2;

	private static final Random rand = new Random();

	public boolean isUpdating;

	private Set<Entity> entities;

	private ExtraInformation[][] extras;
	private AbstractRaster[][] map;

	/**
	 * 
	 */
	public Spielfeld() {
		map = new AbstractRaster[MAP_SIZE_X][MAP_SIZE_Y];
		extras = new ExtraInformation[MAP_SIZE_X][MAP_SIZE_Y];

		entities = Collections
				.newSetFromMap(new ConcurrentHashMap<Entity, Boolean>());

		lastUPSMeasure = System.nanoTime();
		generateAndPopulateMap();
	}

	/**
	 * 
	 * @param e
	 */
	public void deSpawn(Entity e) {
		if (e != null) {
			synchronized (entities) {
				entities.remove(e);
			}
			if (Main.getRenderHelper() != null)
				Main.getRenderHelper().deSpawn(e);
		}
	}

	public Collection<Entity> getEntitiesInRect(int posX, int posY, int sizeX,
			int sizeY) {
		Collection<Entity> ret = Collections
				.synchronizedCollection(new Vector<Entity>());

		synchronized (entities) {
			Iterator<Entity> i = entities.iterator();
			Entity e;

			while (i.hasNext()) {
				e = i.next();
				if (e.isInside(posX, posY, sizeX, sizeY))
					ret.add(e);
			}
		}

		return ret;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Entity getEntityAt(int x, int y) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return null;

		synchronized (entities) {
			Iterator<Entity> i = entities.iterator();
			while (i.hasNext()) {
				Entity e = i.next();
				if (e.getPosX() == x && e.getPosY() == y)
					return e;
			}
		}
		return null;
	}

	/**
	 * 
	 */
	public Set<Entity> getEntityList() {
		synchronized (entities) {
			return entities;
		}
	}

	/**
	 * 
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
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public EntityItem getItemAt(int x, int y) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return null;

		synchronized (entities) {
			Iterator<Entity> i = entities.iterator();
			while (i.hasNext()) {
				Entity e = i.next();
				if (e.getPosX() == x && e.getPosY() == y
						&& e instanceof EntityItem)
					return (EntityItem) e;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public int getMapSizeX() {
		return MAP_SIZE_X;
	}

	/**
	 * 
	 * @return
	 */
	public int getMapSizeY() {
		return MAP_SIZE_Y;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public EntityNPC getNPCAt(int x, int y) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return null;

		synchronized (entities) {
			Iterator<Entity> i = entities.iterator();
			while (i.hasNext()) {
				Entity e = i.next();
				if (e.getPosX() == x && e.getPosY() == y
						&& e instanceof EntityNPC)
					return (EntityNPC) e;
			}
		}
		return null;
	}

	//
	// /**
	// *
	// * @return
	// */
	// @Deprecated
	// public int getPlayerX() {
	// return SpielerpositionX;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// @Deprecated
	// public int getPlayerY() {
	// return SpielerpositionY;
	// }

	/**
	 * 
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
	 * 
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
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isEntityAtPos(int x, int y) {
		return getEntityAt(x, y) != null;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isItemAtPos(int x, int y) {
		return getEntityAt(x, y) instanceof EntityItem;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isNPCAtPos(int x, int y) {
		return getEntityAt(x, y) instanceof EntityNPC;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param r
	 */
	public void setRasterAt(int x, int y, AbstractRaster r) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return;
		map[x][y] = r;
		extras[x][y] = r.getExtraInformation();
	}

	/**
	 * 
	 * @param e
	 */
	public void spawn(Entity e) {
		if (e != null) {
			synchronized (entities) {
				if (!entities.contains(e))
					entities.add(e);
			}
			if (Main.getRenderHelper() != null)
				Main.getRenderHelper().spawn(e);
		}
	}

	/**
	 * Updated das Spielfeld
	 */
	public void update() {

		isUpdating = true;

		Direction d = Main.getInputManager().getNextDirection();

		Main.getFigur().moveBy(d);

		// if (d != Direction.UNKNOWN
		// && getRasterAt(SpielerpositionX + d.getOffsetX(),
		// SpielerpositionY + d.getOffsetY())
		// .canWalkOnFromDirection(
		// SpielerpositionX + d.getOffsetX(),
		// SpielerpositionY + d.getOffsetY(),
		// d.getOpposite())) {
		//

		// }

		synchronized (entities) {
			Iterator<Entity> i = entities.iterator();
			while (i.hasNext()) {
				Entity e = i.next();
				if (e != null && e.canUpdate())
					e.update();
			}
		}

		isUpdating = false;

		calcUPS();

	}

	/**
	 * 
	 */
	public void generateAndPopulateMap() {
		// RASEN!
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++)
				setRasterAt(x, y, Raster.RASEN);

		}

		// BÄUME!
		for (int i = 0; i < rand.nextInt(51) + 25; i++) {
			setRasterAt(rand.nextInt(MAP_SIZE_X), rand.nextInt(MAP_SIZE_Y),
					Raster.BAUM);
		}

		// KISTEN!
		for (int i = 0; i < rand.nextInt(7) + 3; i++) {
			setRasterAt(rand.nextInt(MAP_SIZE_X), rand.nextInt(MAP_SIZE_Y),
					Raster.KISTE);

		}

		// WÄNDE!
		for (int x = 0; x < map.length; x++) {
			setRasterAt(x, 0, Raster.WAND);
			setRasterAt(x, MAP_SIZE_Y - 1, Raster.WAND);
		}
		for (int y = 0; y < map.length; y++) {
			setRasterAt(0, y, Raster.WAND);
			setRasterAt(MAP_SIZE_X - 1, y, Raster.WAND);
		}

		// KISTENINHALTE!
		for (int x = 0; x < extras.length; x++) {
			for (int y = 0; y < extras[x].length; y++) {
				if (getExtraInformationAt(x, y) instanceof ExtraInformationKiste) {
					((ExtraInformationKiste) getExtraInformationAt(x, y))
							.getInventar().addItem(
									new ItemStack(Items.ITEM_42, 42));
					((ExtraInformationKiste) getExtraInformationAt(x, y))
							.getInventar().addItem(new ItemStack(Items.NUKE));
					((ExtraInformationKiste) getExtraInformationAt(x, y))
							.getInventar().addItem(new ItemStack(Items.KEY));
				}
			}
		}

		// ENTITIES!
		spawn(Main.getFigur());
		spawn(new EntityRedNPC(2, 2, Images.redNPC));

	}

	public int getUPS() {
		return staticUPS;
	}

	private void calcUPS() {
		if (System.nanoTime() - lastUPSMeasure > 1000000000) {
			staticUPS = ups;
			ups = 0;
			lastUPSMeasure += 1000000000;
		}
		ups++;
	}
}