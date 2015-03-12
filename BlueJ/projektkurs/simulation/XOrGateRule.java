package projektkurs.simulation;

public class XOrGateRule extends GateRule {

    private final AndGateRule andGate;
    private final OrGateRule orGate;

    public XOrGateRule(int outputFlowDirection) {
        super(outputFlowDirection);
        andGate = new AndGateRule(outputFlowDirection);
        orGate = new OrGateRule(outputFlowDirection);
    }

    @Override
    public Rule copy() {
        return new XOrGateRule(outputFlowDirection);
    }

    @Override
    public String getGateName() {
        return "XOR";
    }

    @Override
    public int nextInt(Board b, int x, int y) {
        return andGate.nextInt(b, x, y) == 0 && orGate.nextInt(b, x, y) != 0 ? outputFlowDirection : 0;
    }

}
