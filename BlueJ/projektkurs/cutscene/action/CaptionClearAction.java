package projektkurs.cutscene.action;

import projektkurs.cutscene.condition.Condition;

public class CaptionClearAction extends CaptionChangeAction {

	public CaptionClearAction() {
		super(null);
	}

	public CaptionClearAction(Condition condition) {
		super(condition, null);
	}

}
