package projektkurs.entity.behaviour;

/**
 * Alle Beahviours.
 */
public enum Behaviours {

    /**
     * Der Entity tut nichts.
     */
    NOTHING(new BehaviourNothing()),
    /**
     * Der Entity läuft herum.
     */
    RUN_AROUND(new BehaviourRunAround());

    /**
     * Die Behaviour.
     */
    private final IBehaviour bhv;

    /**
     * Kostruktor.
     *
     * @param bhv
     *            Behaviour
     */
    private Behaviours(IBehaviour bhv) {
        this.bhv = bhv;
    }

    /**
     * Die Behaviour.
     *
     * @return Behaviour
     */
    public IBehaviour getBehaviour() {
        return bhv;
    }

}
