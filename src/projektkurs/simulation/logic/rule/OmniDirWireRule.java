package projektkurs.simulation.logic.rule;

import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.LogicBoard;

public class OmniDirWireRule extends Rule {

	@Override
	public Rule copy() {
		return new OmniDirWireRule();
	}

	@Override
	public int getColor(LogicBoard b, int x, int y) {
		return b.getFlow(x, y) != EnumFlow.NONE ? 0xDD00DD : 0;
	}

	@Override
	public String getName() {
		return "OmniDirWire";
	}

	@Override
	public EnumFlow getNextFlow(LogicBoard b, int x, int y) {
		if (b.getFlow(x - 1, y) == EnumFlow.EAST || b.getFlow(x + 1, y) == EnumFlow.WEST || b.getFlow(x, y - 1) == EnumFlow.SOUTH || b.getFlow(x, y + 1) == EnumFlow.NORTH || b.getFlow(x - 1, y) == EnumFlow.OMNI_DIRECTIONAL || b.getFlow(x + 1, y) == EnumFlow.OMNI_DIRECTIONAL
				|| b.getFlow(x, y - 1) == EnumFlow.OMNI_DIRECTIONAL || b.getFlow(x, y + 1) == EnumFlow.OMNI_DIRECTIONAL) {
			return EnumFlow.OMNI_DIRECTIONAL;
		}
		return EnumFlow.NONE;
	}

}
