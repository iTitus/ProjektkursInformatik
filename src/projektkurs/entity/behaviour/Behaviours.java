package projektkurs.entity.behaviour;

public enum Behaviours {

	NOTHING(new BehaviourNothing()), RUN_AROUND(new BehaviourRunAround());

	private IBehaviour bhv;

	private Behaviours(IBehaviour bhv) {
		this.bhv = bhv;
	}

	public IBehaviour getBehaviour() {
		return bhv;
	}

}
