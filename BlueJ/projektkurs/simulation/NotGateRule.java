package projektkurs.simulation;

public class NotGateRule extends Rule {

    private static final int[] TABLE_X = { 0, -1, 0, 1 };
    private static final int[] TABLE_Y = { 1, 0, -1, 0 };
    private final int dir;

    public NotGateRule(int dir) {
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
        return "NOT: " + s;
    }

    @Override
    public int nextInt(Board b, int x, int y) {
        return b.getFlow(x + NotGateRule.TABLE_X[dir - 1], y + NotGateRule.TABLE_Y[dir - 1]) != 0 ? 0 : dir;
    }

}
