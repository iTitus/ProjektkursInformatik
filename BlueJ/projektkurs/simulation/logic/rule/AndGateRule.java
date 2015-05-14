package projektkurs.simulation.logic.rule;

import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.LogicBoard;

public class AndGateRule extends GateRule {

	private static final int[] TABLE_X = {1, 0, -1, 0};
	private static final int[] TABLE_Y = {0, 1, 0, -1};

	public AndGateRule(EnumFlow outputFlowDirection) {
		super(outputFlowDirection);
	}

	@Override
	public Rule copy() {
		return new AndGateRule(outputFlowDirection);
	}

	@Override
	public String getGateName() {
		return "AND";
	}

	@Override
	public EnumFlow getNextFlow(LogicBoard b, int x, int y) {
		EnumFlow flow1 = b.getFlow(x + TABLE_X[outputFlowDirection.getIndex() - 1], y + TABLE_Y[outputFlowDirection.getIndex() - 1]);
		EnumFlow flow2 = b.getFlow(x - TABLE_X[outputFlowDirection.getIndex() - 1], y - TABLE_Y[outputFlowDirection.getIndex() - 1]);
		return (flow1 == EnumFlow.OMNI_DIRECTIONAL || flow1.getIndex() == (4 + outputFlowDirection.getIndex() - 2) % 4 + 1) && (flow2 == EnumFlow.OMNI_DIRECTIONAL || flow2.getIndex() == (4 + outputFlowDirection.getIndex()) % 4 + 1) ? outputFlowDirection : EnumFlow.NONE;
	}

}
