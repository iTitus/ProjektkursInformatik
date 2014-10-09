package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;

public class ConditionedTalkAction extends Action {

	private Condition condition;

	public ConditionedTalkAction(Condition condition) {
		this.condition = condition;
	}

	@Override
	public void doAction(CutScene cutScene) {
		// TODO Auto-generated method stub

	}

	@Override
	public Condition getCondition() {
		return condition;
	}

}
