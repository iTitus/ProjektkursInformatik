package projektkurs.simulation.pacman;

public enum GhostMode {

    CHASE(0.95), FRIGHTENED(0.6), IDLE(0.95), RETURNING_HOME(3), SCATTER(0.95);

    private final double speedModifier;

    private GhostMode() {
        this(1);
    }

    private GhostMode(double speedModifier) {
        this.speedModifier = speedModifier;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }
}
