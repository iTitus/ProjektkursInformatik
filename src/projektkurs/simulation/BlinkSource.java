package projektkurs.simulation;

import projektkurs.Main;

public class BlinkSource extends Rule {

    private final int durationOn, durationOff;

    public BlinkSource(int durationOn, int durationOff) {
        this.durationOn = durationOn;
        this.durationOff = durationOff;
    }

    @Override
    public Rule copy() {
        return new BlinkSource(durationOn, durationOff);
    }

    @Override
    public int getColor(Board b, int x, int y) {
        return 0xBB77BB;
    }

    @Override
    public String getName() {
        return "BlinkSource[ON: " + durationOn + "; OFF:" + durationOff + "]";
    }

    @Override
    public int nextInt(Board b, int x, int y) {
        return Main.getTicks() % (durationOn + durationOff) < durationOn ? -1 : 0;
    }

}
