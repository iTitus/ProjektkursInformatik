package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

public class InvertedCondition extends Condition {

	private final Condition condition;

	public InvertedCondition(Condition condition) {
		this.condition = condition;
	}

	@Override
	public boolean isTrue(Action action, CutScene cutScene) {
		return !condition.isTrue(action, cutScene);
	}
}
