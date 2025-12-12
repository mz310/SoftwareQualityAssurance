package lab14.sict.must.edu.mn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BasicCalculatorTest {

    @Test
    void addsTwoNumbers() {
        BasicCalculator calculator = new BasicCalculator();
        assertEquals(5.0, calculator.add(2.0, 3.0));
    }

    @Test
    void subtractsTwoNumbers() {
        BasicCalculator calculator = new BasicCalculator();
        assertEquals(-1.0, calculator.subtract(2.0, 3.0));
    }
}
