package lab14.sict.must.edu.mn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MultiplicationTest {

    @Test
    void multipliesPositiveNumbers() {
        Multiplication multiplication = new Multiplication();
        assertEquals(12.0, multiplication.multiply(3.0, 4.0));
    }

    @Test
    void multipliesWithNegativeOperand() {
        Multiplication multiplication = new Multiplication();
        assertEquals(-12.0, multiplication.multiply(-3.0, 4.0));
    }
}
