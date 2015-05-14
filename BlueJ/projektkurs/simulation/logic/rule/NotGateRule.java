package projektkurs.simulation.logic.rule;

import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.LogicBoard;

public class NotGateRule extends GateRule {

	private static final int[] TABLE_X = {0, -1, 0, 1};
	private static final int[] TABLE_Y = {1, 0, -1, 0};

	public NotGateRule(EnumFlow outputFlowDirection) {
		super(outputFlowDirection);
	}

	@Override
	public Rule copy() {
		return new NotGateRule(outputFlowDirection);
	}

	@Override
	public String getGateName() {
		return "NOT";
	}

	@Override
	public EnumFlow getNextFlow(LogicBoard b, int x, int y) {
		return b.getFlow(x + NotGateRule.TABLE_X[outputFlowDirection.getIndex() - 1], y + NotGateRule.TABLE_Y[outputFlowDirection.getIndex() - 1]) != EnumFlow.NONE ? EnumFlow.NONE : outputFlowDirection;
	}

}
