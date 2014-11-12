package projektkurs.entity;

import java.awt.image.BufferedImage;

public abstract class EntityNPC extends EntityLiving {

	public EntityNPC(int posX, int posY, BufferedImage image, int maxHealth) {
		this(posX, posY, 1, 1, image, maxHealth);
	}

	public EntityNPC(int posX, int posY, int sizeX, int sizeY,
			BufferedImage image, int maxHealth) {
		super(posX, posY, sizeX, sizeY, image, maxHealth);
	}

}
