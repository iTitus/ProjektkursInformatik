package projektkurs.world;

import java.util.Random;

import projektkurs.Main;
import projektkurs.item.Item;
import projektkurs.lib.Direction;
import projektkurs.lib.Integers;
import projektkurs.world.raster.BaumRaster;
import projektkurs.world.raster.KistenRaster;
import projektkurs.world.raster.RasenRaster;
import projektkurs.world.raster.Raster;
import projektkurs.world.raster.WandRaster;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationKiste;

/**
 * TEMPORÄRE MAP!
 * 
 */
public class TempMapBuilder {

	private static final int MAP_SIZE_X = Integers.SIGHT_X * 2;
	private static final int MAP_SIZE_Y = Integers.SIGHT_Y * 2;

	private static final Random rand = new Random();

	private ExtraInformation[][] extras;
	private Raster[][] map;

	private int SpielerpositionX;
	private int SpielerpositionY;

	public TempMapBuilder() {
		map = new Raster[MAP_SIZE_X][MAP_SIZE_Y];
		extras = new ExtraInformation[MAP_SIZE_X][MAP_SIZE_Y];

		generateMap();

		SpielerpositionX = (int) (Integers.SIGHT_X / 2D) + 1;
		SpielerpositionY = (int) (Integers.SIGHT_Y / 2D) + 1;

	}

	public ExtraInformation getExtraInformationAt(int x, int y) {
		if (x < 0 || x >= extras.length || y < 0 || y >= extras[x].length)
			return null;
		return extras[x][y];
	}

	public int getMapSizeX() {
		return MAP_SIZE_X;
	}

	public int getMapSizeY() {
		return MAP_SIZE_Y;
	}

	public int getPlayerX() {
		return SpielerpositionX;
	}

	public int getPlayerY() {
		return SpielerpositionY;
	}

	public Raster getRasterAt(int x, int y) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return null;
		return map[x][y];
	}

	public void setRasterAt(int x, int y, Raster r) {
		if (x < 0 || x >= map.length || y < 0 || y >= map[x].length)
			return;
		map[x][y] = r;
		extras[x][y] = r.getExtraInformation();
	}

	/**
	 * Updated das Spielfeld
	 */
	public void update() {

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

	}

	private void generateMap() {
		// RASEN!
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++)
				setRasterAt(x, y, RasenRaster.getInstance());

		}

		// BÄUME!
		for (int i = 0; i < rand.nextInt(51) + 25; i++) {
			setRasterAt(rand.nextInt(MAP_SIZE_X), rand.nextInt(MAP_SIZE_Y),
					BaumRaster.getInstance());
		}

		// KISTEN!
		for (int i = 0; i < rand.nextInt(7) + 3; i++) {
			setRasterAt(rand.nextInt(MAP_SIZE_X), rand.nextInt(MAP_SIZE_Y),
					KistenRaster.getInstance());

		}

		// WÄNDE!
		for (int x = 0; x < map.length; x++) {
			setRasterAt(x, 0, WandRaster.getInstance());
			setRasterAt(x, MAP_SIZE_Y - 1, WandRaster.getInstance());
		}
		for (int y = 0; y < map.length; y++) {
			setRasterAt(0, y, WandRaster.getInstance());
			setRasterAt(MAP_SIZE_X - 1, y, WandRaster.getInstance());
		}

		// KISTENINHALTE!
		for (int x = 0; x < extras.length; x++) {
			for (int y = 0; y < extras[x].length; y++) {
				if (getExtraInformationAt(x, y) instanceof ExtraInformationKiste) {
					((ExtraInformationKiste) getExtraInformationAt(x, y))
							.getInventar().addItem(Item.NUKE);
					((ExtraInformationKiste) getExtraInformationAt(x, y))
							.getInventar().addItem(Item.KEY);
					((ExtraInformationKiste) getExtraInformationAt(x, y))
							.getInventar().addItem(Item.ITEM_42);
				}
			}
		}

	}
}