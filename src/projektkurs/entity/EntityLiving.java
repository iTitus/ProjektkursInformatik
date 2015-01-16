package projektkurs.entity;

import projektkurs.lib.Strings;
import projektkurs.render.Sprite;
import projektkurs.util.SaveData;
import projektkurs.world.Spielfeld;

/**
 * Ein lebender Entity.
 */
public abstract class EntityLiving extends Entity {

    /**
     * Aktuelle Gesundheit.
     */
    private int health;
    /**
     * Maximale Gesundheit.
     */
    private int maxHealth;

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     */
    public EntityLiving(Spielfeld map) {
        super(map);
    }

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Höhe
     * @param maxHealth
     *            maximale Gesundheit
     * @param sprites
     *            Bilder
     */
    public EntityLiving(Spielfeld map, int posX, int posY, int sizeX, int sizeY, int maxHealth, Sprite... sprites) {
        super(map, posX, posY, sizeX, sizeY, sprites);
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param maxHealth
     *            maximale Gesundheit
     * @param sprites
     *            Bilder
     */
    public EntityLiving(Spielfeld map, int posX, int posY, int maxHealth, Sprite... sprites) {
        this(map, posX, posY, 1, 1, maxHealth, sprites);
    }

    /**
     * Schadet um die gegebene Menge an Gesundheit.
     *
     * @param by
     *            Menge an Gesundheit
     */
    public void damage(int by) {
        health -= by;
        if (health > maxHealth) {
            health = maxHealth;
        }
        if (health < 0) {
            health = 0;
            setDead();
        }
    }

    /**
     * Die aktuelle Gesundheit.
     *
     * @return Gsundheit
     */
    public int getHealth() {
        return health;
    }

    /**
     * Die maximale Gesundheit.
     *
     * @return maximale Gesundheit
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Heilt um die gegebene Menge an Gesundheit.
     *
     * @param by
     *            Menge an Gesundheit
     */
    public void heal(int by) {
        damage(-by);
    }

    @Override
    public void load(SaveData data) {
        super.load(data);
        health = data.getInteger(Strings.ENTITY_HEALTH);
        maxHealth = data.getInteger(Strings.ENTITY_MAX_HEALTH);
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set(Strings.ENTITY_HEALTH, health);
        data.set(Strings.ENTITY_MAX_HEALTH, health);
    }

}
