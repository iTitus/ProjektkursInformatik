package projektkurs.simulation;

public abstract class Rule {

    public abstract int getColor(Board b, int x, int y);

    public abstract String getName();

    public abstract int nextInt(Board b, int x, int y);

}
