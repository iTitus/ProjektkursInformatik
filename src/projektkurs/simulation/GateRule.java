package projektkurs.simulation;

public abstract class GateRule extends Rule {

    protected int outputFlowDirection;

    public GateRule(int outputFlowDirection) {
        this.outputFlowDirection = outputFlowDirection;
    }

    public int getActivatedColor() {
        return 0xFFCCFF;
    }

    @Override
    public int getColor(Board b, int x, int y) {
        return b.getFlow(x, y) != 0 ? getActivatedColor() : getDeactivatedColor();
    }

    public int getDeactivatedColor() {
        return 0xCCFFCC;
    }

    public abstract String getGateName();

    @Override
    public String getName() {
        String s = "";
        switch (outputFlowDirection) {
            case 1:
                s = "N";
                break;
            case 2:
                s = "E";
                break;
            case 3:
                s = "S";
                break;
            case 4:
                s = "W";
                break;
            default:
                break;
        }
        return getGateName() + ": " + s;
    }

}
