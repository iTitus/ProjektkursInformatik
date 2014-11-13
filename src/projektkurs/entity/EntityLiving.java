package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.lib.Strings;
import projektkurs.util.SaveData;

public abstract class EntityLiving extends Entity {

	private int health;
	private int maxHealth;

	public EntityLiving() {
		super();
	}

	public EntityLiving(int posX, int posY, BufferedImage image, int maxHealth) {
		this(posX, posY, 1, 1, image, maxHealth);
	}

	public EntityLiving(int posX, int posY, int sizeX, int sizeY,
			BufferedImage image, int maxHealth) {
		super(posX, posY, sizeX, sizeY, image);
		this.maxHealth = health = maxHealth;
	}

	public void damage(int by) {
		health -= by;
		if (health > maxHealth)
			health = maxHealth;
		if (health < 0) {
			health = 0;
			setDead();
		}
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
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
