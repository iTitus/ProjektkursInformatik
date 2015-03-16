package projektkurs.simulation;

import projektkurs.util.Direction;

public abstract class GateRule extends Rule {

    protected EnumFlow outputFlowDirection;

    public GateRule(EnumFlow outputFlowDirection) {
        this.outputFlowDirection = outputFlowDirection;
    }

    public int getActivatedColor() {
        return 0xFFCCFF;
    }

    @Override
    public int getColor(Board b, int x, int y) {
        return b.getFlow(x, y) != EnumFlow.NONE ? getActivatedColor() : getDeactivatedColor();
    }

    @Override
    public EnumConnectionType getConnectionType(Board b, int x, int y, Direction direction) {
        return super.getConnectionType(b, x, y, direction);
    }

    public int getDeactivatedColor() {
        return 0xCCFFCC;
    }

    public abstract String getGateName();

    @Override
    public String getName() {
        return getGateName() + ": " + outputFlowDirection.getName();
    }

}
