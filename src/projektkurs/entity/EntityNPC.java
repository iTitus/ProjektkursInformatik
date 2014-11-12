package projektkurs.entity;

import java.awt.image.BufferedImage;

public abstract class EntityNPC extends EntityLiving {

	public EntityNPC(int posX, int posY, BufferedImage image, int maxHealth) {
		this(posX, posY, image.getWidth(), image.getHeight(), image, maxHealth);
	}

	public EntityNPC(int posX, int posY, int sizeX, int sizeY,
			BufferedImage image, int maxHealth) {
		super(posX, posY, sizeX, sizeY, image, maxHealth);
	}

}
