package lab14.sict.must.edu.mn;

public class Division {

    private static final double EPSILON = 1.0E-9;

    public double divide(double dividend, double divisor) {
        if (Math.abs(divisor) < EPSILON) {
            throw new IllegalArgumentException("Cannot divide by zero or near-zero values");
        }
        return dividend / divisor;
    }
}
