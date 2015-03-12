package projektkurs.simulation;

public class OmniDirWireRule extends Rule {

    @Override
    public int getColor(Board b, int x, int y) {
        switch (b.getFlow(x, y)) {
            case 0:
                return 0x000000;
            default:
                return 0xDD00DD;
        }
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
