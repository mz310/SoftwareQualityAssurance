package lab14.sict.must.edu.mn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DivisionTest {

  @Test
  void dividesNumbersNormally() {
    Division division = new Division();
    assertEquals(2.5, division.divide(5.0, 2.0));
  }

  @Test
  void throwsWhenDividingByZero() {
    Division division = new Division();
    assertThrows(IllegalArgumentException.class, () -> division.divide(5.0, 0.0));
  }
}
