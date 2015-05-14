package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

/**
 * Abstrakte Condition.
 */
public abstract class Condition {

	/**
	 * Ist diese Condition jetzt true.
	 *
	 * @param action   Die aktuelle Action
	 * @param cutScene Aktuelle CutScene
	 * @return true, wenn ja; false, wenn nein
	 */
	public abstract boolean isTrue(Action action, CutScene cutScene);

}
