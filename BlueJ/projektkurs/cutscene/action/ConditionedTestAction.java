package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;
import projektkurs.cutscene.object.CutSceneObject;

public class ConditionedTestAction extends Action {

	private Condition condition;
	private CutSceneObject cutSceneObject;

	public ConditionedTestAction(Condition condition,
			CutSceneObject cutSceneObject) {
		this.condition = condition;
		this.cutSceneObject = cutSceneObject;
	}

	@Override
	public void doAction(CutScene cutScene) {
		cutSceneObject.setPos(100, 100);
	}

	@Override
	public Condition getCondition() {
		return condition;
	}

}
