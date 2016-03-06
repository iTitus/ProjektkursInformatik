package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;
import projektkurs.cutscene.condition.TrueCondition;

/**
 * Abstrakte Action.
 */
public abstract class Action {

    /**
     * Ausfuehrbedingung.
     */
    private final Condition condition;

    /**
     * Konstruktor mit der Ausfuehrbedingung 'TrueCondition'.
     */
    public Action() {
        this(new TrueCondition());
    }

    /**
     * Konstruktor.
     * @param condition
     * Ausfuehrbedingung
     */
    public Action(Condition condition) {
        if (condition != null) {
            this.condition = condition;
        } else {
            this.condition = new TrueCondition();
        }
    }

    /**
     * Fuehrt die Action aus.
     * @param cutScene
     * Aktuelle CutScene
     */
    public abstract void doAction(CutScene cutScene);

    /**
     * Die Bedingung, um diese Action auszufuehren.
     * @return Condition
     */
    public final Condition getCondition() {
        return condition;
    }

    /**
     * Soll diese Action ausgefuehrt werden.
     * @param cutScene
     * Aktuelle CutScene
     * @return true, wenn ja; false, wenn nein
     */
    public final boolean shouldDoAction(CutScene cutScene) {
        return condition.isTrue(this, cutScene);
    }
}
