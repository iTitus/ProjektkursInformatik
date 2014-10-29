package projektkurs.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityNPC;
import projektkurs.entity.NPC_testguy;
import projektkurs.item.ItemStack;
import projektkurs.item.Items;
import projektkurs.lib.Direction;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.world.raster.AbstractRaster;
import projektkurs.world.raster.Raster;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationKiste;

/**
 * TEMPORÄRE MAP!
 * 
 */
@SuppressWarnings("unused")
public class TempMapBuilder {

	private static final int MAP_SIZE_X = Integers.SIGHT_X * 2;
	private static final int MAP_SIZE_Y = Integers.SIGHT_Y * 2;

	private static final Random rand = new Random();

	public boolean isUpdating;

	private Collection<Entity> entities;

	private ExtraInformation[][] extras;
	private AbstractRaster[][] map;

	private int SpielerpositionX;
	private int SpielerpositionY;

	/**
	 * 
	 */
	public TempMapBuilder() {
		map = new AbstractRaster[MAP_SIZE_X][MAP_SIZE_Y];
		extras = new ExtraInformation[MAP_SIZE_X][MAP_SIZE_Y];

		entities = Collections.synchronizedCollection(new ArrayList<Entity>());

		generateMap();

		SpielerpositionX = (int) (Integers.SIGHT_X / 2D) + 1;
		SpielerpositionY = (int) (Integers.SIGHT_Y / 2D) + 1;

	}

	/**
	 * 
	 * @param e
	 */
	public void deSpawn(Entity e) {
		synchronized (entities) {
			entities.remove(e);
		}
	}

	public Collection<Entity> getEntitiesInRec(int posX, int posY, int sizeX,
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
	public Collection<Entity> getEntityList() {
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

	/**
	 * 
	 * @return
	 */
	public int getPlayerX() {
		return SpielerpositionX;
	}

	/**
	 * 
	 * @return
	 */
	public int getPlayerY() {
		return SpielerpositionY;
	}

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
	 * @param itemToSet
	 */
	public void setItemAt(int x, int y, EntityItem itemToSet) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return;
		synchronized (entities) {
			entities.add(itemToSet);
		}

	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param npc
	 */
	public void setNPCAt(int x, int y, EntityNPC npc) {

		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return;
		synchronized (entities) {
			entities.add(npc);
		}

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
		}
	}

	/**
	 * Updated das Spielfeld
	 */
	public void update() {

		isUpdating = true;

		Direction d = Main.getInputManager().getNextDirection();

		if (d != Direction.UNKNOWN
				&& getRasterAt(SpielerpositionX + d.getOffsetX(),
						SpielerpositionY + d.getOffsetY())
						.canWalkOnFromDirection(
								SpielerpositionX + d.getOffsetX(),
								SpielerpositionY + d.getOffsetY(),
								d.getOpposite())) {

			if (!(SpielerpositionX + d.getOffsetX() < 0 || SpielerpositionX
					+ d.getOffsetX() > MAP_SIZE_X - 1)) {
				Main.getRenderHelper().moveSight(d.getOffsetX(), 0);
				SpielerpositionX += d.getOffsetX();
			}
			if (!(SpielerpositionY + d.getOffsetY() < 0 || SpielerpositionY
					+ d.getOffsetY() > MAP_SIZE_Y - 1)) {
				Main.getRenderHelper().moveSight(0, d.getOffsetY());
				SpielerpositionY += d.getOffsetY();
			}
		}

		synchronized (entities) {
			Iterator<Entity> i = entities.iterator();
			while (i.hasNext()) {
				Entity e = i.next();
				if (e != null && e.canUpdate())
					e.update();
			}
		}

		isUpdating = false;

	}

	/**
	 * 
	 */
	private void generateMap() {
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
		spawn(new NPC_testguy(2, 2, Images.test_guy));

	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isEntityAtPos(int x, int y) {
		return getEntityAt(x, y) != null;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isItemAtPos(int x, int y) {
		return getEntityAt(x, y) instanceof EntityItem;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isNPCAtPos(int x, int y) {
		return getEntityAt(x, y) instanceof EntityNPC;
	}
}