package projektkurs.simulation;

public class OneWayWireRule extends GateRule {

    private static final int[] TABLE_X = { 0, -1, 0, 1 };
    private static final int[] TABLE_Y = { 1, 0, -1, 0 };

    public OneWayWireRule(EnumFlow outputFlowDirection) {
        super(outputFlowDirection);
    }

    @Override
    public Rule copy() {
        return new OneWayWireRule(outputFlowDirection);
    }

    @Override
    public int getActivatedColor() {
        return 0x44FF44;
    }

    @Override
    public int getDeactivatedColor() {
        return 0;
    }

    @Override
    public String getGateName() {
        return "Diode";
    }

    @Override
    public EnumFlow getNextFlow(Board b, int x, int y) {
        EnumFlow flow = b.getFlow(x + TABLE_X[outputFlowDirection.getIndex() - 1], y + TABLE_Y[outputFlowDirection.getIndex() - 1]);
        return flow == EnumFlow.OMNI_DIRECTIONAL || flow == outputFlowDirection ? outputFlowDirection : EnumFlow.NONE;
    }

}
