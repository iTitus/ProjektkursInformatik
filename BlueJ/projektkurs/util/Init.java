package projektkurs.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Jede öffentlich statische Methode ohne Parameter mit dieser Annotation wird automatisch beim Start in der richtigen Phase ausgeführt.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Init {

    /**
     * Start-Phasen.
     */
    public static enum State {
        /**
         * Mittlere Startphase.
         */
        INIT,
        /**
         * Späte Startphase.
         */
        POST,
        /**
         * Frühe Startphase.
         */
        PRE,
        /**
         * Startphase für Resoureceloading.
         */
        RESOURCES
    }

    /**
     * Startphase, in der diese Methode ausgeführt werden soll - default ist INIT.
     */
    State state() default State.INIT;
}
