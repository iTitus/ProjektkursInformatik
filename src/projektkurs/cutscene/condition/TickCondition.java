package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

/**
 * Eine Condition, die auf den vegangenen Ticks beruht.
 */
public class TickCondition extends Condition {

    /**
     * Verschiedene Typen von Vergleichen der vegangenen Ticks.
     */
    public static enum TickConditionType {
        /**
         * Die vergangenen Ticks stimmen mit der gegebenen Tickzahl ueberein.
         */
        EQUALS,
        /**
         * Die vergangenen Ticks sind groesser als die gegebene Tickzahl.
         */
        GREATER,
        /**
         * Die vergangenen Ticks sind groesser als die gegebene Tickzahl oder stimmen mit ihr ueberein.
         */
        GREATER_EQUALS,
        /**
         * Die vergangenen Ticks sind kleiner als die gegebene Tickzahl.
         */
        LESSER,
        /**
         * Die vergangenen Ticks sind kleiner als die gegebene Tickzahl oder stimmen mit ihr ueberein.
         */
        LESSER_EQUALS,
        /**
         * Die vergangenen Ticks 'modulo' die gegebene Tickzahl ist 0.
         */
        MODULO_0
    }

    /**
     * Die gegebene Tickzahl.
     */
    private final int ticks;

    /**
     * Der TickConditionType, mit dem verglichen werden soll.
     */
    private final TickConditionType type;

    /**
     * Konstruktor.
     * @param type
     * Der TickConditionType, mit dem verglichen werden soll
     * @param ticks
     * Die gegebene Tickzahl
     */
    public TickCondition(TickConditionType type, int ticks) {
        this.type = type;
        this.ticks = ticks;
    }

    @Override
    public boolean isTrue(Action action, CutScene cutScene) {

        switch (type) {
            case EQUALS:
                return cutScene.getElapsedTicks() == ticks;
            case LESSER:
                return cutScene.getElapsedTicks() < ticks;
            case LESSER_EQUALS:
                return cutScene.getElapsedTicks() <= ticks;
            case GREATER:
                return cutScene.getElapsedTicks() > ticks;
            case GREATER_EQUALS:
                return cutScene.getElapsedTicks() >= ticks;
            case MODULO_0:
                return cutScene.getElapsedTicks() % ticks == 0;
            default:
                return false;
        }

    }
}
