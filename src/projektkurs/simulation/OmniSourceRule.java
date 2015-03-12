package projektkurs.simulation;

public class OmniSourceRule extends Rule {

    @Override
    public int getColor(Board b, int x, int y) {
        return 0xFF01FF;
    }

    @Override
    public String getName() {
        return "OmniSource";
    }

    @Override
    public int nextInt(Board b, int x, int y) {
        return -1;
    }

}
