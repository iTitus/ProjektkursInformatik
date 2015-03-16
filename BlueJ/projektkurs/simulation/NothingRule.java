package projektkurs.simulation;

import projektkurs.lib.Integers;
import projektkurs.util.Direction;

public class NothingRule extends Rule {

    @Override
    public Rule copy() {
        return new NothingRule();
    }

    @Override
    public int getColor(Board b, int x, int y) {
        return Integers.TRANSPARENCY;
    }

    @Override
    public EnumConnectionType getConnectionType(Board b, int x, int y, Direction direction) {
        return EnumConnectionType.DISCONNECTED;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public EnumFlow getNextFlow(Board b, int x, int y) {
        return EnumFlow.NONE;
    }

}
