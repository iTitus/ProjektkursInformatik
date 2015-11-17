package projektkurs.simulation.tower;

public enum TowerDefenseBoardType {

    STANDARD(100, 50) {

        @Override
        public void generate(TowerDefenseBoard board) {
            // TODO Auto-generated method stub
        }

    };

    private final int sizeX, sizeY;

    private TowerDefenseBoardType(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public abstract void generate(TowerDefenseBoard board);
}
