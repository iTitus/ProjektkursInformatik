package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

public class DelayedCondition extends Condition {

	private final Condition condition;
	private final int delay;
	private int counter;

	public DelayedCondition(Condition condition, int delay) {
		this.delay = delay;
		this.condition = condition;
		counter = -1;
	}

	@Override
	public boolean isTrue(Action action, CutScene cutScene) {
		if (counter < 0 && condition.isTrue(action, cutScene)) {
			counter = cutScene.getElapsedTicks();
		}
		if (counter >= 0 && cutScene.getElapsedTicks() - counter >= delay) {
			return true;
		}
		return false;
	}
}
