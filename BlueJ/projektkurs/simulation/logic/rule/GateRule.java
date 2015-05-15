package projektkurs.simulation.logic.rule;

import projektkurs.simulation.logic.EnumConnectionType;
import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.LogicBoard;
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
    public int getColor(LogicBoard b, int x, int y) {
        return b.getFlow(x, y) != EnumFlow.NONE ? getActivatedColor() : getDeactivatedColor();
    }

    @Override
    public EnumConnectionType getConnectionType(LogicBoard b, int x, int y, Direction direction) {
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
