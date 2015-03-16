package projektkurs.simulation;

public class XOrGateRule extends GateRule {

    private final Rule andGate, orGate;

    public XOrGateRule(EnumFlow outputFlowDirection) {
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
    public EnumFlow getNextFlow(Board b, int x, int y) {
        return andGate.getNextFlow(b, x, y) == EnumFlow.NONE && orGate.getNextFlow(b, x, y) != EnumFlow.NONE ? outputFlowDirection : EnumFlow.NONE;
    }

}
