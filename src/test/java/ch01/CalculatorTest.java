package ch01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void plus() {
        Assertions.assertEquals(3, Calculator.plus(1, 2));
        Assertions.assertEquals(5, Calculator.plus(4, 1));
    }
}
