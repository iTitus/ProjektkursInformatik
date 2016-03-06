package projektkurs.util;

/**
 * Ein 2-dimensionaler Vektor.
 */
public class Vector2d {

    /**
     * Einheitsvektor X.
     */
    public static final Vector2d X = new Vector2d(1, 0);
    /**
     * Einheitsvektor Y.
     */
    public static final Vector2d Y = new Vector2d(0, 1);

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
     * @param x
     * X-Komponente
     * @param y
     * Y-Komponente
     */
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gibt einen Vektor zurueck, bei dem der gegebene Wert jeweils zu den beiden Komponenten addiert wurde.
     * @param d
     * Summand
     * @return Vektor
     */
    public Vector2d add(double d) {
        return new Vector2d(x + d, y + d);
    }

    /**
     * Gibt einen Vektor zurueck, bei dem die Komponenten des gegebenen Vektors jeweils zu beiden Komponenten dieses Vektors addiert wurden.
     * @param v
     * Summand-Vektor
     * @return Vektor
     */
    public Vector2d add(Vector2d v) {
        return new Vector2d(x + v.x, y + v.y);
    }

    /**
     * Der Winkel zwischen diesem Vektor und dem gebenen Vektor im Gradmass.
     * @param v
     * Vektor
     * @return Winkel im Gradmass
     */
    public double angleBetweenDeg(Vector2d v) {
        return Math.toDegrees(angleBetweenRad(v));
    }

    /**
     * Der Winkel zwischen diesem Vektor und dem gebenen Vektor im Bogenmass.
     * @param v
     * Vektor
     * @return Winkel im Bogenmass
     */
    public double angleBetweenRad(Vector2d v) {
        return thetaRad() - v.thetaRad();
    }

    /**
     * Kopiert diesen Vektor.
     * @return kopierter Vektor
     */
    public Vector2d copy() {
        return new Vector2d(x, y);
    }

    /**
     * Das Kreuzprodukt dieses Vektors mit dem gegegeben Vektor. Da dieser Vektor 2-dimensional ist, ist das Ergebnis eine Zahl.
     * @param v
     * Vektor
     * @return Kreuz-Vektor
     */
    public double cross(Vector2d v) {
        return x * v.getY() - y * v.getX();
    }

    /**
     * Gibt einen Vektor zurueck, bei dem beide Komponenten jeweils durch den gegebenen Wert dividiert wurden.
     * @param d
     * Divisor
     * @return Vektor
     */
    public Vector2d div(double d) {
        return new Vector2d(x / d, y / d);
    }

    /**
     * Gibt einen Vektor zurueck, bei dem beide Komponenten dieses Vektors jeweils durch die Komponenten des gegebenen Vektors dividiert wurden.
     * @param v
     * Divisor-Vektor
     * @return Vektor
     */
    public Vector2d div(Vector2d v) {
        return new Vector2d(x / v.x, y / v.y);
    }

    /**
     * Das Skalarprodukt (Punktprodukt) dieses Vektors mit dem gegegeben Vektor.
     * @param v
     * Vektor
     * @return Skalar
     */
    public double dot(Vector2d v) {
        return x * v.getX() + y * v.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector2d) {
            Vector2d v = (Vector2d) o;
            return x == v.x && y == v.y;

        }
        return false;
    }

    /**
     * Die gerundete X-Komponente.
     * @return X-Komponente
     */
    public int getIntX() {
        return MathUtil.floor(x);
    }

    /**
     * Die gerundete Y-Komponente.
     * @return Y-Komponente
     */
    public int getIntY() {
        return MathUtil.floor(y);
    }

    /**
     * Der Betrag/Die Laenge des Vektors.
     * @return Betrag/Laenge
     */
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Die X-Komponente.
     * @return X-Komponente
     */
    public double getX() {
        return x;
    }

    /**
     * Die Y-Komponente.
     * @return Y-Komponente
     */
    public double getY() {
        return y;
    }

    @Override
    public int hashCode() {
        long bits = Double.doubleToLongBits(x);
        bits ^= Double.doubleToLongBits(y) * 31;
        return (int) bits ^ (int) (bits >> 32);
    }

    /**
     * Die Lineare Interpolation dieses Vektors mit dem gegebenen Vektor und dem gegebenen Faktor.
     * @param dest
     * Vektor
     * @param lerpFactor
     * Faktor
     * @return lineare Interpolation
     */
    public Vector2d lerp(Vector2d dest, double lerpFactor) {
        return dest.sub(this).mul(lerpFactor).add(this);
    }

    /**
     * Gibt einen Vektor zurueck, bei dem beide Komponenten jeweils mit dem gegebenen Wert multipliziert wurden.
     * @param d
     * Produkt
     * @return Vektor
     */
    public Vector2d mul(double d) {
        return new Vector2d(x * d, y * d);
    }

    /**
     * Gibt einen Vektor zurueck, bei dem beide Komponenten dieses Vektors jeweils mit den Komponenten des gegebenen Vektors multipliziert wurden.
     * @param v
     * Produkt-Vektor
     * @return Vektor
     */
    public Vector2d mul(Vector2d v) {
        return new Vector2d(x * v.x, y * v.y);
    }

    /**
     * Gibt den normalisierten Vektor mit der Laenge 1 zurueck.
     * @return normalisierter Vektor
     */
    public Vector2d normalize() {
        return div(getLength());
    }

    /**
     * Rotiert diesen Vektor um die gegebene Gradzahl im Gradmass.
     * @param angleDeg
     * Winkel im Gradmass
     * @return rotierter Vektor
     */
    public Vector2d rotateDeg(double angleDeg) {
        return rotateRad(Math.toRadians(angleDeg));
    }

    /**
     * Rotiert diesen Vektor um die gegebene Gradzahl im Bogenmass.
     * @param angleRad
     * Winkel im Bogenmass
     * @return rotierter Vektor
     */
    public Vector2d rotateRad(double angleRad) {
        double cosX = Math.cos(angleRad);
        double sinY = Math.sin(angleRad);
        return new Vector2d(x * cosX - y * sinY, x * sinY + y * cosX);
    }

    /**
     * Gibt einen Vektor zurueck, bei dem der gegebene Wert von beiden Komponenten jeweils subtrahiert wurde.
     * @param d
     * Subtrahend
     * @return Vektor
     */
    public Vector2d sub(double d) {
        return new Vector2d(x - d, y - d);
    }

    /**
     * Gibt einen Vektor zurueck, bei dem die Komponenten des gegebenen Vektors jeweils von beiden Komponenten dieses Vektors subtrahiert wurden.
     * @param v
     * Subtrahend-Vektor
     * @return Vektor
     */
    public Vector2d sub(Vector2d v) {
        return new Vector2d(x - v.y, y - v.y);
    }

    /**
     * Der Winkel der Polarkoordinate im Gradmass.
     * @return Winkel im Gradmass
     */
    public double thetaDeg() {
        return Math.toDegrees(thetaRad());
    }

    /**
     * Der Winkel der Polarkoordinate im Bogenmass.
     * @return Winkel im Bogenmass
     */
    public double thetaRad() {
        return Math.atan2(y, x);
    }

    @Override
    public String toString() {
        return "(" + x + "|" + y + ")";
    }
}
