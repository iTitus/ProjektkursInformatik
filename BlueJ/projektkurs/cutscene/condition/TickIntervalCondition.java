package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

/**
 * 
 *
 */
public class TickIntervalCondition extends Condition {

	private int minTick, maxTick;

	/**
	 * 
	 * @param minTick
	 * @param maxTick
	 */
	public TickIntervalCondition(int minTick, int maxTick) {
		this.minTick = minTick;
		this.maxTick = maxTick;
	}

	@Override
	public boolean isTrue(Action action, CutScene cutScene) {
		return cutScene.getElapsedTicks() >= minTick
				&& cutScene.getElapsedTicks() <= maxTick;
	}

}
