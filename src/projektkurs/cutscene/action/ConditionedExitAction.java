package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;

public class ConditionedExitAction extends Action {

	private final Condition condition;

	public ConditionedExitAction(Condition condition) {
		this.condition = condition;
	}

	@Override
	public void doAction(CutScene cutScene) {
		cutScene.setFinished();
	}

	@Override
	public Condition getCondition() {
		return condition;
	}

}
