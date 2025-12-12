package lab14.sict.must.edu.mn;

public class Division {

  public double divide(double dividend, double divisor) {
    if (divisor == 0.0) {
      throw new IllegalArgumentException("Cannot divide by zero");
    }
    return dividend / divisor;
  }
}
