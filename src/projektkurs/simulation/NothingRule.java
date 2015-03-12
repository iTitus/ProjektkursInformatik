package projektkurs.simulation;

import projektkurs.lib.Integers;

public class NothingRule extends Rule {

    @Override
    public int getColor(Board b, int x, int y) {
        return Integers.TRANSPARENCY;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int nextInt(Board b, int x, int y) {
        return 0;
    }

}
