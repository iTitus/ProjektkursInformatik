package projektkurs.simulation;

public class OneWayWireRule extends GateRule {

    public OneWayWireRule(int outputFlowDirection) {
        super(outputFlowDirection);
    }

    @Override
    public Rule copy() {
        return new OneWayWireRule(outputFlowDirection);
    }

    @Override
    public int getActivatedColor() {
        return 0x00FF00;
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
    public int nextInt(Board b, int x, int y) {
        // TODO Auto-generated method stub
        return 0;
    }

}
