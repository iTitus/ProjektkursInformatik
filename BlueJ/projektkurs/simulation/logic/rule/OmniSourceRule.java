package projektkurs.simulation.logic.rule;

import projektkurs.simulation.logic.EnumConnectionType;
import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.LogicBoard;
import projektkurs.util.Direction;

public class OmniSourceRule extends Rule {

	@Override
	public Rule copy() {
		return new OmniSourceRule();
	}

	@Override
	public int getColor(LogicBoard b, int x, int y) {
		return 0xFF01FF;
	}

	@Override
	public EnumConnectionType getConnectionType(LogicBoard b, int x, int y, Direction direction) {
		return EnumConnectionType.OUTPUT_ONLY;
	}

	@Override
	public String getName() {
		return "OmniSource";
	}

	@Override
	public EnumFlow getNextFlow(LogicBoard b, int x, int y) {
		return EnumFlow.OMNI_DIRECTIONAL;
	}

}
