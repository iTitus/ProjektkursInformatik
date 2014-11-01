package projektkurs.entity.behaviour;

import java.util.Random;

import projektkurs.entity.Entity;

public class BehaviourRunAround implements IBehaviour {

	private static Random rand = new Random();

	@Override
	public void onTick(Entity e) {
		if (rand.nextInt(35) == 0) {
			e.moveBy(rand.nextInt(3) - 1, rand.nextInt(3) - 1);
		}
	}
}
