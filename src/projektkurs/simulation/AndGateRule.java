package projektkurs.simulation;

public class AndGateRule extends Rule {

    private static final int[] TABLE_X = { 1, 0, -1, 0 };
    private static final int[] TABLE_Y = { 0, 1, 0, -1 };
    private final int dir;

    public AndGateRule(int dir) {
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
        return "AND: " + s;
    }

    @Override
    public int nextInt(Board b, int x, int y) {
        int flow1 = b.getFlow(x + TABLE_X[dir - 1], y + TABLE_Y[dir - 1]);
        int flow2 = b.getFlow(x - TABLE_X[dir - 1], y - TABLE_Y[dir - 1]);
        return (flow1 == -1 || flow1 == (4 + dir - 2) % 4 + 1) && (flow2 == -1 || flow2 == (4 + dir) % 4 + 1) ? dir : 0;
    }

}
