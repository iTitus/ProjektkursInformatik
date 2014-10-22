package projektkurs.entity.behavior;

import java.util.Random;

import projektkurs.entity.Entity;

public class Bhvr_Runaround implements IBehavior {

	private static Random rand = new Random();

	@Override
	public void onTick(Entity e) {
		if (rand.nextInt(2) > 0) {
			e.moveBy(rand.nextInt(3) - 1, rand.nextInt(3) - 1);
		}

	}
}
