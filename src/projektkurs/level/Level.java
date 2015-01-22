package projektkurs.level;

import projektkurs.Main;
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
public class Level implements IUpdatable {

    /**
     * Aktueller Spielfeld-Index.
     */
    private int currentMap;
    /**
     * Alle Spielfelder.
     */
    private final Spielfeld[] maps;
    /**
     * Name dieses Levels.
     */
    private final String name;

    /**
     * Konstruktor.
     *
     * @param name
     *            Name
     * @param maps
     *            alle Spielfelder
     */
    public Level(String name, Spielfeld... maps) {
        this.maps = maps;
        currentMap = 0;
        this.name = name;
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

    /**
     * Das aktuelle Spielfeld.
     *
     * @return Spielfeld
     */
    public Spielfeld getMap() {
        return maps[currentMap];
    }

    /**
     * Das Spielfeld am gegebenen Index.
     *
     * @param i
     *            Index
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

    /**
     * Rendert das Level.
     *
     * @param g
     *            Screen
     */
    public void render(Screen s) {
        getMap().render(s);
    }

    /**
     * Waehlt das Spielfeld am Index aus und setzt den Spieler an die Spawn-Koordinaten.
     *
     * @param i
     *            Index
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

    @Override
    public void update() {
        getMap().update();
    }
}
