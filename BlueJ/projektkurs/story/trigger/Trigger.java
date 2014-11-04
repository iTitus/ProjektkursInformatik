package projektkurs.story.trigger;

import java.lang.reflect.Method;

import projektkurs.util.ReflectionUtil;

/**
 * Ein abstrakter Trigger
 *
 */
public abstract class Trigger {

	protected Method m;

	protected Trigger(Method m) {
		this.m = m;
	}

	public void doTrigger() {
		ReflectionUtil.invokeStatic(m);
	}

	/**
	 * 
	 * @return
	 */
	public abstract boolean isTriggerActive();
}
