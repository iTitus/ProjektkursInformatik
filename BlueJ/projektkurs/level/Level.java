package projektkurs.level;

import projektkurs.Main;
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
     * Aktuelles Spielfeld.
     */
    private Spielfeld map;
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
        map = maps[0];
        this.name = name;
    }

    @Override
    public boolean canUpdate() {
        return true;
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
        return map;
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
     * Wählt das Spielfeld am Index aus und setzt den Spieler an die Spawn-Koordinaten.
     *
     * @param i
     *            Index
     */
    public void setMap(int i) {
        if (MathUtil.isInArray(i, maps.length)) {
            map = maps[i];
            Main.getPlayer().setPosition(map.getSpawnX(), map.getSpawnY());
        } else {
            Logger.logThrowable("Unable to set map", new ArrayIndexOutOfBoundsException(i));
        }
    }

    @Override
    public void update() {
        if (map.canUpdate()) {
            map.update();
        }
    }
}
