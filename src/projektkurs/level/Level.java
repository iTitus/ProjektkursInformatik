package projektkurs.level;

import projektkurs.Main;
import projektkurs.dialog.DialogManager;
import projektkurs.io.storage.ISaveable;
import projektkurs.io.storage.SaveData;
import projektkurs.render.Screen;
import projektkurs.util.IUpdatable;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;
import projektkurs.world.builder.MapBuilder;

/**
 * Ein Level.
 */
public class Level implements IUpdatable, ISaveable {

	/**
	 * Der Dialogmanger.
	 */
	private DialogManager dialogManager;
	/**
	 * Alle Spielfelder.
	 */
	private Spielfeld[] maps;
	/**
	 * Name dieses Levels.
	 */
	private String name;
	/**
	 * Aktueller Spielfeld-Index.
	 */
	private int currentMap;

	public Level() {
		//NO-OP
	}

	/**
	 * Konstruktor.
	 *
	 * @param name Name
	 * @param maps alle Spielfelder
	 */
	public Level(String name, Spielfeld... maps) {
		this.maps = maps;
		currentMap = 0;
		this.name = name;
		dialogManager = new DialogManager();
	}

	@Override
	public boolean canUpdate() {
		return getMap().canUpdate();
	}

	/**
	 * Generiert alle Spielfelder.
	 */
	public void generateAndPopulateAll() {
		String methodName;
		for (int i = 0; i < maps.length; i++) {
			methodName = "generateAndPopulate";
			methodName += name;
			methodName += "Map";
			methodName += i;
			ReflectionUtil.invokeStatic(ReflectionUtil.getMethod(MapBuilder.class, methodName, Spielfeld.class), maps[i]);
		}
	}

	public DialogManager getDialogManager() {
		return dialogManager;
	}

	/**
	 * Das aktuelle Spielfeld.
	 *
	 * @return Spielfeld
	 */
	public Spielfeld getMap() {
		return maps[currentMap];
	}

	/**
	 * Waehlt das Spielfeld am Index aus und setzt den Spieler an die Spawn-Koordinaten.
	 *
	 * @param i Index
	 */
	public void setMap(int i) {
		if (MathUtil.isInArray(i, maps.length)) {
			getMap().deSpawn(Main.getPlayer());
			currentMap = i;
			Main.getPlayer().setMap(getMap());
			Main.getPlayer().setPosition(getMap().getSpawnX(), getMap().getSpawnY());
			getMap().spawn(Main.getPlayer());
		} else {
			Logger.logThrowable("Unable to set map", new ArrayIndexOutOfBoundsException(i));
		}
	}

	/**
	 * Das Spielfeld am gegebenen Index.
	 *
	 * @param i Index
	 * @return Spielfeld
	 */
	public Spielfeld getMapAt(int i) {
		if (MathUtil.isInArray(i, maps.length)) {
			return maps[i];
		}
		Logger.logThrowable("Unable to get map", new ArrayIndexOutOfBoundsException(i));
		return null;
	}

	/**
	 * Anzahl der Maps.
	 *
	 * @return Anzahl
	 */
	public int getMapCount() {
		return maps.length;
	}

	/**
	 * Name.
	 *
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	@Override
	public void load(SaveData data) {
		name = data.getString("name");
		currentMap = data.getInteger("currentMap");
		maps = new Spielfeld[data.getInteger("mapCount")];
		for (int i = 0; i < maps.length; i++) {
			maps[i] = new Spielfeld(this);
			maps[i].load(data.getSaveData("map" + i));
		}
		dialogManager = new DialogManager();
		dialogManager.load(data.getSaveData("dialogManager"));
	}

	/**
	 * Rendert das Level.
	 *
	 * @param s Screen
	 */
	public void render(Screen s) {
		if (getMap() != null) {
			getMap().render(s);
		}
	}

	@Override
	public void update() {
		if (getMap() != null) {
			getMap().update();
		}
	}

	@Override
	public void write(SaveData data) {
		data.set("name", name);
		data.set("currentMap", currentMap);
		data.set("mapCount", maps.length);
		for (int i = 0; i < maps.length; i++) {
			SaveData saveData = new SaveData();
			maps[i].write(saveData);
			data.set("map" + i, saveData);
		}
		SaveData dialogManagerData = new SaveData();
		dialogManager.write(dialogManagerData);
		data.set("dialogManager", dialogManagerData);
	}
}
