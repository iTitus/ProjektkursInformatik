package projektkurs.util;

public class Rectangle implements IHasPositionAndSize<Double, Double> {

    private double posX, posY, sizeX, sizeY;

    public Rectangle(double posX, double posY, double sizeX, double sizeY) {
        setPosition(posX, posY);
        setSize(sizeX, sizeY);
    }

    @Override
    public Double getPosX() {
        return posX;
    }

    @Override
    public Double getPosY() {
        return posY;
    }

    @Override
    public Double getSizeX() {
        return sizeX;
    }

    @Override
    public Double getSizeY() {
        return sizeY;
    }

    @Override
    public void setPosition(Double posX, Double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void setSize(Double sizeX, Double sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

}
