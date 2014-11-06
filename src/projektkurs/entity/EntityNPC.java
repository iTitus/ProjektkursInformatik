package projektkurs.entity;

import java.awt.image.BufferedImage;

public class EntityNPC extends Entity {

	private int health;
	private final int maxHealth;

	public EntityNPC(int posX, int posY, BufferedImage image, int maxHealth) {
		super(posX, posY, image);
		this.maxHealth = health = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

}
