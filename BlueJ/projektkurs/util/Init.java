package projektkurs.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Jede oeffentlich statische Methode ohne Parameter mit dieser Annotation wird automatisch beim Start in der richtigen Phase ausgefuehrt.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Init {

	/**
	 * Startphase, in der diese Methode ausgefuehrt werden soll - default ist INIT.
	 */
	State value() default State.INIT;

	/**
	 * Start-Phasen.
	 */
	public static enum State {
		/**
		 * Mittlere Startphase.
		 */
		INIT,
		/**
		 * Spaete Startphase.
		 */
		POST,
		/**
		 * Fruehe Startphase.
		 */
		PRE,
		/**
		 * Mittlere Startphase fuer Resoureceloading.
		 */
		RESOURCES,
		/**
		 * Spaete Startphase fuer Resoureceloading.
		 */
		RESOURCES_POST,
		/**
		 * Fruehe Startphase fuer Resoureceloading.
		 */
		RESOURCES_PRE
	}
}
