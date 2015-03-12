package projektkurs.simulation;

public class NotGateRule extends GateRule {

    private static final int[] TABLE_X = { 0, -1, 0, 1 };
    private static final int[] TABLE_Y = { 1, 0, -1, 0 };

    public NotGateRule(int outputFlowDirection) {
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
    public int nextInt(Board b, int x, int y) {
        return b.getFlow(x + NotGateRule.TABLE_X[outputFlowDirection - 1], y + NotGateRule.TABLE_Y[outputFlowDirection - 1]) != 0 ? 0 : outputFlowDirection;
    }

}
