package projektkurs.util;

/**
 * Ein 2-dimensionaler Vektor.
 */
public class Vector2d {

    /**
     * Sind diese zwei Vektoren gleich.
     *
     * @param a
     *            1. Vektor
     * @param b
     *            2. Vektor
     * @return true, wenn ja; false, wenn nein.
     */
    public static boolean equals(Vector2d a, Vector2d b) {
        return a.x == b.x && a.y == b.y;
    }

    /**
     * X-Komponente.
     */
    private final double x;
    /**
     * Y-Komponente.
     */
    private final double y;

    /**
     * Konstruktor.
     *
     * @param x
     *            X-Komponente
     * @param y
     *            Y-Komponente
     */
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gibt einen Vektor zurück, bei dem der gegebene Wert jeweils zu den beiden Komponenten addiert wurde.
     *
     * @param d
     *            Summand
     * @return Vektor
     */
    public Vector2d add(double d) {
        return new Vector2d(x + d, y + d);
    }

    /**
     * Gibt einen Vektor zurück, bei dem die Komponenten des gegebenen Vektors jeweils zu beiden Komponenten dieses Vektors addiert wurden.
     *
     * @param v
     *            Summand-Vektor
     * @return Vektor
     */
    public Vector2d add(Vector2d v) {
        return new Vector2d(x + v.x, y + v.y);
    }

    /**
     * Das Kreuzprodukt dieses Vektors mit dem gegegeben Vektor. Da dieser Vektor 2-dimensional ist, ist das Ergebnis eine Zahl.
     *
     * @param v
     *            Vektor
     * @return Kreuz-Vektor
     */
    public double cross(Vector2d v) {
        return x * v.getY() - y * v.getX();
    }

    /**
     * Gibt einen Vektor zurück, bei dem beide Komponenten jeweils durch den gegebenen Wert dividiert wurden.
     *
     * @param d
     *            Divisor
     * @return Vektor
     */
    public Vector2d div(double d) {
        return new Vector2d(x / d, y / d);
    }

    /**
     * Gibt einen Vektor zurück, bei dem beide Komponenten dieses Vektors jeweils durch die Komponenten des gegebenen Vektors dividiert wurden.
     *
     * @param v
     *            Divisor-Vektor
     * @return Vektor
     */
    public Vector2d div(Vector2d v) {
        return new Vector2d(x / v.x, y / v.y);
    }

    /**
     * Das Skalarprodukt (Punktprodukt) dieses Vektors mit dem gegegeben Vektor.
     *
     * @param v
     *            Vektor
     * @return Skalar
     */
    public double dot(Vector2d v) {
        return x * v.getX() + y * v.getY();
    }

    /**
     * Die X-Komponente.
     *
     * @return X-Komponente
     */
    public double getX() {
        return x;
    }

    /**
     * Die Y-Komponente.
     *
     * @return Y-Komponente
     */
    public double getY() {
        return y;
    }

    /**
     * Betrag/Länge des Vektors.
     *
     * @return Betrag/Länge
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Die Lineare Interpolation dieses Vektors mit dem gegebenen Vektor und dem gegebenen Faktor.
     *
     * @param dest
     *            Vektor
     * @param lerpFactor
     *            Faktor
     * @return lineare Interpolation
     */
    public Vector2d lerp(Vector2d dest, double lerpFactor) {
        return dest.sub(this).mul(lerpFactor).add(this);
    }

    /**
     * Gibt einen Vektor zurück, bei dem beide Komponenten jeweils mit dem gegebenen Wert multipliziert wurden.
     *
     * @param d
     *            Produkt
     * @return Vektor
     */
    public Vector2d mul(double d) {
        return new Vector2d(x * d, y * d);
    }

    /**
     * Gibt einen Vektor zurück, bei dem beide Komponenten dieses Vektors jeweils mit den Komponenten des gegebenen Vektors multipliziert wurden.
     *
     * @param v
     *            Produkt-Vektor
     * @return Vektor
     */
    public Vector2d mul(Vector2d v) {
        return new Vector2d(x * v.x, y * v.y);
    }

    /**
     * Gibt den normalisierten Vektor mit der Länge 1 zurück.
     *
     * @return normalisierter Vektor
     */
    public Vector2d normalize() {
        return div(length());
    }

    /**
     * Rotiert diesen Vektor um die gegebene Gradzahl im Gradmaß.
     *
     * @param angleDeg
     *            Winkel im Gradmaß
     * @return rotierter Vektor
     */
    public Vector2d rotateDeg(double angleDeg) {
        return rotateRad(Math.toRadians(angleDeg));
    }

    /**
     * Rotiert diesen Vektor um die gegebene Gradzahl im Bogenmaß.
     *
     * @param angleRad
     *            Winkel im Bogenmaß
     * @return rotierter Vektor
     */
    public Vector2d rotateRad(double angleRad) {
        double cos = Math.cos(angleRad);
        double sin = Math.sin(angleRad);
        return new Vector2d(x * cos - y * sin, x * sin + y * cos);
    }

    /**
     * Gibt einen Vektor zurück, bei dem der gegebene Wert von beiden Komponenten jeweils subtrahiert wurde.
     *
     * @param d
     *            Subtrahend
     * @return Vektor
     */
    public Vector2d sub(double d) {
        return new Vector2d(x - d, y - d);
    }

    /**
     * Gibt einen Vektor zurück, bei dem die Komponenten des gegebenen Vektors jeweils von beiden Komponenten dieses Vektors subtrahiert wurden.
     *
     * @param v
     *            Subtrahend-Vektor
     * @return Vektor
     */
    public Vector2d sub(Vector2d v) {
        return new Vector2d(x - v.y, y - v.y);
    }

    @Override
    public String toString() {
        return "(" + x + "|" + y + ")";
    }
}
