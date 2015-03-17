package projektkurs.simulation.logic.rule;

import projektkurs.Main;
import projektkurs.simulation.logic.EnumConnectionType;
import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.LogicBoard;
import projektkurs.util.Direction;

public class BlinkSourceRule extends Rule {

    private final int durationOn, durationOff;

    public BlinkSourceRule(int durationOn, int durationOff) {
        this.durationOn = durationOn;
        this.durationOff = durationOff;
    }

    @Override
    public Rule copy() {
        return new BlinkSourceRule(durationOn, durationOff);
    }

    @Override
    public int getColor(LogicBoard b, int x, int y) {
        return 0xBB77BB;
    }

    @Override
    public EnumConnectionType getConnectionType(LogicBoard b, int x, int y, Direction direction) {
        return EnumConnectionType.OUTPUT_ONLY;
    }

    @Override
    public String getName() {
        return "BlinkSource[ON: " + durationOn + "; OFF:" + durationOff + "]";
    }

    @Override
    public EnumFlow getNextFlow(LogicBoard b, int x, int y) {
        return Main.getTicks() % (durationOn + durationOff) < durationOn ? EnumFlow.OMNI_DIRECTIONAL : EnumFlow.NONE;
    }

}
