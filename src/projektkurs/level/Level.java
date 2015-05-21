package projektkurs.level;

import java.util.ArrayList;
import java.util.List;

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
     * Aktueller Spielfeld-Index.
     */
    private int currentMap;
    /**
     * Der Dialogmanger.
     */
    private DialogManager dialogManager;
    /**
     * Alle Spielfelder.
     */
    private List<Spielfeld> maps;
    /**
     * Name dieses Levels.
     */
    private String name;

    public Level() {
        // NO-OP
    }

    /**
     * Konstruktor.
     *
     * @param name
     *            Name
     * @param maps
     *            alle Spielfelder
     */
    public Level(String name) {
        maps = new ArrayList<Spielfeld>();
        currentMap = 0;
        this.name = name;
        dialogManager = new DialogManager();
    }

    public void addMap(Spielfeld map) {
        if (map == null) {
            throw new NullPointerException("Map may not be null");
        }
        if (map.getLevel() != this) {
            throw new IllegalArgumentException("Map is not in this level");
        }
        maps.add(map);
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
        for (int i = 0; i < maps.size(); i++) {
            methodName = "generateAndPopulate";
            methodName += name;
            methodName += "Map";
            methodName += i;
            ReflectionUtil.invokeStatic(ReflectionUtil.getMethod(MapBuilder.class, methodName, Spielfeld.class), maps.get(i));
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
        return maps.get(currentMap);
    }

    /**
     * Das Spielfeld am gegebenen Index.
     *
     * @param i
     *            Index
     * @return Spielfeld
     */
    public Spielfeld getMapAt(int i) {
        if (MathUtil.isInArray(i, maps.size())) {
            return maps.get(i);
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
        return maps.size();
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
        int mapCount = data.getInteger("mapCount");
        maps = new ArrayList<Spielfeld>(mapCount);
        for (int i = 0; i < mapCount; i++) {
            maps.set(i, new Spielfeld(this));
            maps.get(i).load(data.getSaveData("map" + i));
        }
        dialogManager = new DialogManager();
        dialogManager.load(data.getSaveData("dialogManager"));
    }

    /**
     * Rendert das Level.
     *
     * @param s
     *            Screen
     */
    public void render(Screen s) {
        if (getMap() != null) {
            getMap().render(s);
        }
    }

    /**
     * Waehlt das Spielfeld am Index aus und setzt den Spieler an die Spawn-Koordinaten.
     *
     * @param i
     *            Index
     */
    public void setMap(int i) {
        if (MathUtil.isInArray(i, maps.size())) {
            getMap().deSpawn(Main.getPlayer());
            currentMap = i;
            Main.getPlayer().setMap(getMap());
            Main.getPlayer().setPosition(getMap().getSpawnX(), getMap().getSpawnY());
            getMap().spawn(Main.getPlayer());
        } else {
            Logger.logThrowable("Unable to set map", new ArrayIndexOutOfBoundsException(i));
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
        data.set("mapCount", maps.size());
        for (int i = 0; i < maps.size(); i++) {
            SaveData saveData = new SaveData();
            maps.get(i).write(saveData);
            data.set("map" + i, saveData);
        }
        SaveData dialogManagerData = new SaveData();
        dialogManager.write(dialogManagerData);
        data.set("dialogManager", dialogManagerData);
    }
}
