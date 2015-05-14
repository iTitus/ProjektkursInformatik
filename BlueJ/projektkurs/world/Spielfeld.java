package projektkurs.world;

import java.util.ArrayList;
import java.util.List;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.io.storage.ISaveable;
import projektkurs.io.storage.SaveData;
import projektkurs.level.Level;
import projektkurs.lib.Entities;
import projektkurs.lib.ExtraInformationen;
import projektkurs.lib.Integers;
import projektkurs.lib.Raster;
import projektkurs.lib.Sprites;
import projektkurs.raster.AbstractRaster;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.IHasExtraInformation;
import projektkurs.render.Screen;
import projektkurs.story.StoryManager;
import projektkurs.util.IUpdatable;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

/**
 * Ein Spielfeld.
 */
public class Spielfeld implements IUpdatable, ISaveable {

	private final Level level;
	/**
	 * Alle Entities.
	 */
	private List<Entity> entities;
	/**
	 * Alle ExtraInformationen.
	 */
	private List<ExtraInformation> extras;
	/**
	 * Alle Raster.
	 */
	private int[] raster;
	/**
	 * Spielfeldbreite.
	 */
	private int sizeX;
	/**
	 * Spielfeldhoehe.
	 */
	private int sizeY;
	/**
	 * Der Storymanger.
	 */
	private StoryManager storyManager;
	/**
	 * X-Koordinate des Spielererscheinungsortes.
	 */
	private int spawnX;
	/**
	 * Y-Koordinate des Spielererscheinungsortes.
	 */
	private int spawnY;

	public Spielfeld(Level level) {
		this.level = level;
	}

	/**
	 * Konstrukor.
	 *
	 * @param sizeX  Spielfeldbreite
	 * @param sizeY  Spielfeldhoehe
	 * @param spawnX X-Koordinate des Spielererscheinungsortes
	 * @param spawnY Y-Koordinate des Spielererscheinungsortes
	 */
	public Spielfeld(Level level, int sizeX, int sizeY, int spawnX, int spawnY) {
		this.level = level;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.spawnX = MathUtil.clampToArray(spawnX, sizeX);
		this.spawnY = MathUtil.clampToArray(spawnY, sizeY);
		raster = new int[sizeX * sizeY];
		extras = new ArrayList<ExtraInformation>();
		entities = new ArrayList<Entity>();
		storyManager = new StoryManager();
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	/**
	 * Kopiert das Spielfeld.
	 *
	 * @return Spielfeld
	 */

	public Spielfeld copy() {
		Spielfeld world = new Spielfeld(level, sizeX, sizeY, spawnX, spawnY);

		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				world.setRasterAt(x, y, getRasterAt(x, y));
				ExtraInformation extra = world.getExtraInformationAt(x, y);
				ExtraInformation thisExtra = getExtraInformationAt(x, y);
				if (extra != null && thisExtra != null) {
					SaveData data = new SaveData();
					thisExtra.write(data);
					extra.load(data);
				}
			}
		}

		return world;
	}

	/**
	 * Laesst einen Entity verschwinden.
	 *
	 * @param e Entity
	 */
	public void deSpawn(Entity e) {
		if (e != null) {
			getEntityList().remove(e);
		}
	}

	/**
	 * Findet alle Entities an der gegebenen Position.
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @return gefundener Entity
	 */
	public List<Entity> getEntitiesAt(int x, int y) {
		ArrayList<Entity> ret = new ArrayList<Entity>();
		ret.addAll(getEntitiesInRect(x, y, 1, 1));
		return ret;
	}

	/**
	 * Findet alle Entities in einem gegebenen Rechteck.
	 *
	 * @param posX  X-Koordinate
	 * @param posY  Y-Koordinate
	 * @param sizeX Breite
	 * @param sizeY Hoehe
	 * @return gefundene Entities
	 */
	public ArrayList<Entity> getEntitiesInRect(int posX, int posY, int sizeX, int sizeY) {
		ArrayList<Entity> ret = new ArrayList<Entity>();

		for (Entity e : getEntityList()) {
			if (e.isInside(posX, posY, sizeX, sizeY)) {
				ret.add(e);
			}
		}

		return ret;
	}

	/**
	 * Alle Entities auf dieser Map.
	 *
	 * @return alle Entities
	 */
	public List<Entity> getEntityList() {
		return entities;
	}

	/**
	 * Findet die ExtraInformation an der gegebenen Position.
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @return gefundene ExtraInformation
	 */
	public ExtraInformation getExtraInformationAt(int x, int y) {
		if (isInMap(x, y)) {
			for (ExtraInformation extra : getExtraInformationList()) {
				if (extra.getPosX() == x && extra.getPosY() == y) {
					return extra;
				}
			}
		}
		return null;
	}

	/**
	 * Alle ExtraInformationen auf dieser Map.
	 *
	 * @return alle ExtraInformationen
	 */
	public List<ExtraInformation> getExtraInformationList() {
		return extras;
	}

	public Level getLevel() {
		return level;
	}

	/**
	 * Breite der Map.
	 *
	 * @return Breite
	 */
	public int getMapSizeX() {
		return sizeX;
	}

	/**
	 * Hoehe der Map.
	 *
	 * @return Hoehe
	 */
	public int getMapSizeY() {
		return sizeY;
	}

	/**
	 * Findet das Raster an der gegebenen Position.
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @return gefundenes Raster
	 */
	public AbstractRaster getRasterAt(int x, int y) {
		if (isInMap(x, y)) {
			return Raster.RASTER[raster[x + y * sizeX]];
		}
		return null;
	}

	/**
	 * X-Koordinate des Spieler-Erscheinungsortes.
	 *
	 * @return X-Koordinate
	 */
	public int getSpawnX() {
		return spawnX;
	}

	/**
	 * Y-Koordinate des Spieler-Erscheinungsortes.
	 *
	 * @return Y-Koordinate
	 */
	public int getSpawnY() {
		return spawnY;
	}

	/**
	 * Der StoryManager dieser Map.
	 *
	 * @return StoryManager
	 */
	public StoryManager getStoryManager() {
		return storyManager;
	}

	/**
	 * Ist ein Entity an der gegebenen Position.
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @return true, wenn ja; false, wenn nein
	 */
	public boolean isEntityAtPos(int x, int y) {
		return getEntitiesAt(x, y) != null;
	}

	/**
	 * Prueft ob die gegebene Koordinate innerhalb dieser Map ist.
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @return true, wenn ja; false, wenn nicht
	 */
	public boolean isInMap(int x, int y) {
		return MathUtil.isInArray(x, sizeX) && MathUtil.isInArray(y, sizeY);
	}

	/**
	 * Ist ein Raster an der gegebenen Position.
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @return true, wenn ja; false, wenn nein
	 */
	public boolean isRasterAt(int x, int y) {
		if (isInMap(x, y)) {
			return raster[x + y * sizeX] > 0;
		}
		return false;
	}

	/**
	 * Entfernt eine ExtraInformation von dieser Map.
	 *
	 * @param extra die zu entfernende ExtraInformation
	 */
	public void removeExtraInformation(ExtraInformation extra) {
		getExtraInformationList().remove(extra);
	}

	/**
	 * Rendert das Spielfeld.
	 *
	 * @param s Screen
	 */
	public void render(Screen s) {
		for (int y = Main.getRenderHelper().getSightY(); y < Main.getRenderHelper().getSightY() + Integers.sightY; y++) {
			for (int x = Main.getRenderHelper().getSightX(); x < Main.getRenderHelper().getSightX() + Integers.sightX; x++) {
				if (isRasterAt(x, y)) {
					getRasterAt(x, y).render(s, this, x, y);
				} else {
					RenderUtil.drawDefaultRaster(s, Sprites.tree, x, y);
				}
			}
		}

		for (Entity e : getEntityList()) {
			if (!e.shouldDeSpawn() && Main.getRenderHelper().isInSight(e)) {
				e.render(s);
			}
		}
	}

	/**
	 * Setzt ein Raster und seine ExtraInformation an die gegebene Position.
	 *
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param r zu setzendes Raster
	 */
	public void setRasterAt(int x, int y, AbstractRaster r) {
		if (isInMap(x, y)) {
			ExtraInformation oldExtra = getExtraInformationAt(x, y);
			if (oldExtra != null) {
				removeExtraInformation(oldExtra);
			}
			if (r != null) {
				raster[x + y * sizeX] = r.getID();
				if (r instanceof IHasExtraInformation) {
					ExtraInformation newExtra = ((IHasExtraInformation) r).createExtraInformation(this, x, y);
					getExtraInformationList().add(newExtra);

				}
			} else {
				raster[x + y * sizeX] = 0;
			}
		}
	}

	/**
	 * Setzt die Koordinaten des Spielererscheinungsortes.
	 *
	 * @param spawnX X-Koordinate
	 * @param spawnY Y-Koordinate
	 */
	public void setSpawn(int spawnX, int spawnY) {
		this.spawnX = spawnX;
		this.spawnY = spawnY;
	}

	/**
	 * Laesst einen Entity erscheinen.
	 *
	 * @param e Entity
	 */
	public void spawn(Entity e) {
		if (e != null) {
			getEntityList().add(e);
		}
	}

	/**
	 * Updated das Spielfeld.
	 */
	@Override
	public void update() {

		Main.getPlayer().moveBy(Main.getInputManager().getNextDirection());

		if (Main.getInputManager().canUpdate()) {
			Main.getInputManager().update();
		}

		for (ExtraInformation extra : getExtraInformationList()) {
			if (extra.canUpdate()) {
				extra.update();
			}
		}

		ArrayList<Entity> toRemove = new ArrayList<Entity>();
		for (Entity e : getEntityList()) {
			if (e != null) {
				if (e.canUpdate()) {
					e.update();
				}
				if (e.shouldDeSpawn()) {
					toRemove.add(e);
				}
			}
		}
		getEntityList().removeAll(toRemove);

		if (storyManager.canUpdate()) {
			storyManager.update();
		}

	}


	@Override
	public void load(SaveData data) {
		sizeX = data.getInteger("sizeX");
		sizeY = data.getInteger("sizeY");
		spawnX = data.getInteger("spawnX");
		spawnY = data.getInteger("spawnY");
		SaveData rasterData = data.getSaveData("raster");
		raster = new int[sizeX * sizeY];
		for (int i = 0; i < raster.length; i++) {
			raster[i] = rasterData.getInteger("raster" + i);
		}
		SaveData entityData = data.getSaveData("entities");
		entities = new ArrayList<Entity>();
		int entityCount = entityData.getInteger("entityCount");
		for (int i = 0; i < entityCount; i++) {
			Entity e = Entities.loadEntity(this, entityData.getSaveData("entity" + i));
			if (e != null) {
				entities.add(e);
			}
		}
		SaveData extraData = data.getSaveData("extras");
		extras = new ArrayList<ExtraInformation>();
		int extraCount = extraData.getInteger("extraCount");
		for (int i = 0; i < extraCount; i++) {
			ExtraInformation extra = ExtraInformationen.loadExtraInformation(this, extraData.getSaveData("extra" + i));
			if (extra != null) {
				extras.add(extra);
			}
		}
		storyManager = new StoryManager();
		storyManager.load(data.getSaveData("storyManager"));
	}

	@Override
	public void write(SaveData data) {
		data.set("sizeX", sizeX);
		data.set("sizeY", sizeY);
		data.set("spawnX", spawnX);
		data.set("spawnY", spawnY);
		SaveData rasterData = new SaveData();
		for (int i = 0; i < raster.length; i++) {
			rasterData.set("raster" + i, raster[i]);
		}
		data.set("raster", rasterData);
		SaveData entityData = new SaveData();
		entityData.set("entityCount", entities.size());
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e != null) {
				entityData.set("entity" + i, Entities.writeEntity(e));
			}
		}
		data.set("entities", entityData);
		SaveData extraData = new SaveData();
		extraData.set("extraCount", extras.size());
		for (int i = 0; i < extras.size(); i++) {
			ExtraInformation extra = extras.get(i);
			if (extra != null) {
				extraData.set("extra" + i, ExtraInformationen.writeExtraInformation(extra));
			}
		}
		data.set("extras", extraData);
		SaveData storyManagerData = new SaveData();
		storyManager.write(storyManagerData);
		data.set("storyManager", storyManager);
	}

}
