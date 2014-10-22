package projektkurs.lib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Jede öffentlich statische Methode ohne Parameter mit dieser Annotation wird
 * automatisch geladen
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Init {

	public static enum State {
		INIT, POST, PRE;
	}

	State state() default State.INIT;
}
