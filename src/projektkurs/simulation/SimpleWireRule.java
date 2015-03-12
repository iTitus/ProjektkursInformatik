package projektkurs.simulation;

public class SimpleWireRule extends Rule {

    /**
     * true: WE; false: NS
     */
    private final boolean orientation;

    public SimpleWireRule(boolean dir) {
        orientation = dir;
    }

    @Override
    public Rule copy() {
        return new SimpleWireRule(orientation);
    }

    @Override
    public int getColor(Board b, int x, int y) {
        switch (b.getFlow(x, y)) {
            case -1:
                return 0xCCCCCC;
            case 0:
                return 0x000000;
            default:
                return 0x00FF00;
        }
    }

    @Override
    public String getName() {
        return "Wire: " + (orientation ? "WE" : "NS");
    }

    @Override
    // 0: no Flow; 1: Flow SN; 2: Flow WE; 3: Flow NS; 4: Flow EW; -1: Omnidirectional
    public int nextInt(Board b, int x, int y) {

        int ret = 0;

        if (orientation) {
            int i = b.getFlow(x + 1, y);
            int j = b.getFlow(x - 1, y);
            if (j == -1 && i == -1) {
                ret = -1;
            } else if (j == -1) {
                if (i == 4) {
                    ret = -1;
                } else {
                    ret = 2;
                }
            } else if (i == -1) {
                if (j == 2) {
                    ret = -1;
                } else {
                    ret = 4;
                }
            } else if (i == 4 && j == 2) {
                ret = -1;
            } else if (i == 4) {
                ret = 4;
            } else if (j == 2) {
                ret = 2;
            }
        } else {
            int k = b.getFlow(x, y + 1);
            int l = b.getFlow(x, y - 1);
            if (l == -1 && k == -1) {
                ret = -1;
            } else if (l == -1) {
                if (k == 1) {
                    ret = -1;
                } else {
                    ret = 3;
                }
            } else if (k == -1) {
                if (l == 3) {
                    ret = -1;
                } else {
                    ret = 1;
                }
            } else if (k == 1 && l == 3) {
                ret = -1;
            } else if (k == 1) {
                ret = 1;
            } else if (l == 3) {
                ret = 3;
            }
        }

        return ret;
    }

}
