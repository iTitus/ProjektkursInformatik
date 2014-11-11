package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

public class CombinedAndCondition extends Condition {

	private Condition[] conditions;

	public CombinedAndCondition(Condition... conditions) {
		this.conditions = conditions;
	}

	@Override
	public boolean isTrue(Action action, CutScene cutScene) {
		for (int i = 0; i < conditions.length; i++) {
			if (!conditions[i].isTrue(action, cutScene))
				return false;
		}
		return true;
	}
}
