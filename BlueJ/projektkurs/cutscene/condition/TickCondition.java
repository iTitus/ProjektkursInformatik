package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

/**
 * 
 *
 */
public class TickCondition extends Condition {

	public static enum TickConditionType {
		EQUALS, GREATER, GREATER_EQUALS, LESSER, LESSER_EQUALS;
	}

	private int ticks;

	private TickConditionType type;

	/**
	 * Konstruktor für TickConditions
	 * 
	 * @param _type
	 * @param _ticks
	 */
	public TickCondition(TickConditionType _type, int _ticks) {
		type = _type;
		ticks = _ticks;
	}

	@Override
	public boolean isTrue(Action action, CutScene cutScene) {

		switch (type) {
		case EQUALS: {
			return cutScene.getElapsedTicks() == ticks;
		}
		case LESSER: {
			return cutScene.getElapsedTicks() < ticks;
		}
		case LESSER_EQUALS: {
			return cutScene.getElapsedTicks() <= ticks;
		}
		case GREATER: {
			return cutScene.getElapsedTicks() > ticks;
		}
		case GREATER_EQUALS: {
			return cutScene.getElapsedTicks() >= ticks;
		}
		default: {
			return false;
		}
		}

	}
}
