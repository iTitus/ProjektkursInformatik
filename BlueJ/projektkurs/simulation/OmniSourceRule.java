package projektkurs.simulation;

import projektkurs.util.Direction;

public class OmniSourceRule extends Rule {

    @Override
    public Rule copy() {
        return new OmniSourceRule();
    }

    @Override
    public int getColor(Board b, int x, int y) {
        return 0xFF01FF;
    }

    @Override
    public EnumConnectionType getConnectionType(Board b, int x, int y, Direction direction) {
        return EnumConnectionType.OUTPUT_ONLY;
    }

    @Override
    public String getName() {
        return "OmniSource";
    }

    @Override
    public EnumFlow getNextFlow(Board b, int x, int y) {
        return EnumFlow.OMNI_DIRECTIONAL;
    }

}
