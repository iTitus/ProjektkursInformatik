package projektkurs.story.trigger;

import java.lang.reflect.Method;

import projektkurs.util.ReflectionUtil;

/**
 * Ein abstrakter Trigger
 */
public abstract class Trigger {

	protected final Method m;
	protected final Object[] objects;

	protected Trigger(Method m, Object... objects) {
		this.m = m;
		this.objects = objects;
	}

	public void doTrigger() {
		ReflectionUtil.invokeStatic(m, objects);
	}

	/**
	 * @return
	 */
	public abstract boolean isTriggerActive();
}
