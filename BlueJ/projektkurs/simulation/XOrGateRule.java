package projektkurs.simulation;

public class XOrGateRule extends Rule {

    private final AndGateRule andGate;
    private final int dir;
    private final OrGateRule orGate;

    public XOrGateRule(int dir) {
        andGate = new AndGateRule(dir);
        orGate = new OrGateRule(dir);
        this.dir = dir;

    }

    @Override
    public int getColor(Board b, int x, int y) {
        return 0;
    }

    @Override
    public String getName() {
        String s = "";
        switch (dir) {
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
        return "XOR: " + s;
    }

    @Override
    public int nextInt(Board b, int x, int y) {
        return andGate.nextInt(b, x, y) == 0 && orGate.nextInt(b, x, y) != 0 ? dir : 0;
    }

}
