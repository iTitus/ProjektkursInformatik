package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.entity.behavior.Behaviours;

public class NPC_testguy extends EntityNPC {

	public NPC_testguy(int posX, int posY, BufferedImage image) {
		super(posX, posY, image, 100);
	}

	@Override
	public Behaviours getBehaviour() {
		return Behaviours.RUN_AROUND;
	}

}
