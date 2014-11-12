package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.entity.behaviour.Behaviours;

public class EntityRedNPC extends EntityNPC {

	public EntityRedNPC(int posX, int posY, BufferedImage image) {
		super(posX, posY, image, 10);
	}

	@Override
	public Behaviours getBehaviour() {
		return Behaviours.RUN_AROUND;
	}

}
