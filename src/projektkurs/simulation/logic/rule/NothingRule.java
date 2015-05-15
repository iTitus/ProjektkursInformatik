package projektkurs.simulation.logic.rule;

import projektkurs.lib.Integers;
import projektkurs.simulation.logic.EnumConnectionType;
import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.LogicBoard;
import projektkurs.util.Direction;

public class NothingRule extends Rule {

    @Override
    public Rule copy() {
        return new NothingRule();
    }

    @Override
    public int getColor(LogicBoard b, int x, int y) {
        return Integers.TRANSPARENCY;
    }

    @Override
    public EnumConnectionType getConnectionType(LogicBoard b, int x, int y, Direction direction) {
        return EnumConnectionType.DISCONNECTED;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public EnumFlow getNextFlow(LogicBoard b, int x, int y) {
        return EnumFlow.NONE;
    }

}
