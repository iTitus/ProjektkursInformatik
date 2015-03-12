package projektkurs.simulation;

public class OmniDirWireRule extends Rule {

    @Override
    public Rule copy() {
        return new OmniDirWireRule();
    }

    @Override
    public int getColor(Board b, int x, int y) {
        return b.getFlow(x, y) != 0 ? 0xDD00DD : 0;
    }

    @Override
    public String getName() {
        return "OmniDirWire";
    }

    @Override
    public int nextInt(Board b, int x, int y) {
        if (b.getFlow(x - 1, y) == 2 || b.getFlow(x + 1, y) == 4 || b.getFlow(x, y - 1) == 3 || b.getFlow(x, y + 1) == 1 || b.getFlow(x - 1, y) == -1 || b.getFlow(x + 1, y) == -1 || b.getFlow(x, y - 1) == -1 || b.getFlow(x, y + 1) == -1) {
            return -1;
        }
        return 0;
    }

}
