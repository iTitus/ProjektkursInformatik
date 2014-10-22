package projektkurs.entity.behavior;

public enum Behaviours {

	NOTHING(new Bhvr_Nothing()), RUN_AROUND(new Bhvr_Runaround());

	private IBehavior bhv;

	private Behaviours(IBehavior bhv) {
		this.bhv = bhv;
	}

	public IBehavior getBehaviour() {
		return bhv;
	}

}
