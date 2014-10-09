package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

/**
 * Abstrakte Condition
 * 
 */
public abstract class Condition {

	/**
	 * Ist diese Bedingung jetzt wahr?
	 * 
	 * @param action
	 * @param cutScene
	 * @return
	 */
	public abstract boolean isTrue(Action action, CutScene cutScene);

}
