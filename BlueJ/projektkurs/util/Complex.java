package projektkurs.util;

public class Complex {

    private final double r, i;

    public Complex() {
        this(0);
    }

    public Complex(double r) {
        this(r, 0);
    }

    public Complex(double r, double i) {
        this.r = r;
        this.i = i;
    }

    public Complex add(Complex c) {
        return new Complex(getReal() + c.getReal(), getImaginary() + c.getImaginary());
    }

    public Complex add(double d) {
        return add(new Complex(d));
    }

    public Complex divide(Complex c) {
        return multiply(c.getMultiplyInverse());
    }

    public Complex divide(double d) {
        return divide(new Complex(d));
    }

    public double getAbsolute() {
        return Math.sqrt(r * r + i * i);
    }

    public Complex getAddInverse() {
        return new Complex(-r, -i);
    }

    public double getImaginary() {
        return i;
    }

    public Complex getMultiplyInverse() {
        return new Complex(r / (r * r + i * i), i / (i * i + r * r));
    }

    public double getReal() {
        return r;
    }

    public Complex multiply(Complex c) {
        return new Complex(getReal() * c.getReal() - getImaginary() * c.getImaginary(), c.getImaginary() * getReal() + c.getReal() * getImaginary());
    }

    public Complex multiply(double d) {
        return multiply(new Complex(d));
    }

    public Complex square() {
        return multiply(this);
    }

    public Complex subtract(Complex c) {
        return add(c.getAddInverse());
    }

    public Complex subtract(double d) {
        return subtract(new Complex(d));
    }

    @Override
    public String toString() {
        return String.format("%.2f + %.2fi", r, i);
    }

}
