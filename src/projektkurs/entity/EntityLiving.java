package projektkurs.entity;

import java.awt.image.BufferedImage;

public abstract class EntityLiving extends Entity {

	private int health;
	private final int maxHealth;

	public EntityLiving(int posX, int posY, BufferedImage image, int maxHealth) {
		this(posX, posY, image.getWidth(), image.getHeight(), image, maxHealth);
	}

	public EntityLiving(int posX, int posY, int sizeX, int sizeY,
			BufferedImage image, int maxHealth) {
		super(posX, posY, sizeX, sizeY, image);
		this.maxHealth = health = maxHealth;
	}

	public void damage(int by) {
		health += by;
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

}
