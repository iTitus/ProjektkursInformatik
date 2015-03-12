package projektkurs.simulation;

public class OrGateRule extends GateRule {

    private static final int[] TABLE_X = { 1, 0, -1, 0 };
    private static final int[] TABLE_Y = { 0, 1, 0, -1 };

    public OrGateRule(int outputFlowDirection) {
        super(outputFlowDirection);
    }

    @Override
    public Rule copy() {
        return new OrGateRule(outputFlowDirection);
    }

    @Override
    public String getGateName() {
        return "OR";
    }

    @Override
    public int nextInt(Board b, int x, int y) {
        int flow1 = b.getFlow(x + TABLE_X[outputFlowDirection - 1], y + TABLE_Y[outputFlowDirection - 1]);
        int flow2 = b.getFlow(x - TABLE_X[outputFlowDirection - 1], y - TABLE_Y[outputFlowDirection - 1]);
        return flow1 == -1 || flow1 == (4 + outputFlowDirection - 2) % 4 + 1 || flow2 == -1 || flow2 == (4 + outputFlowDirection) % 4 + 1 ? outputFlowDirection : 0;
    }

}
