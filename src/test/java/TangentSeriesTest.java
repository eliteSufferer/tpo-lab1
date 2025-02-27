import org.example.TangentSeries;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("TangentSeries Calculation Tests")
class TangentSeriesTest {
    private static final double DELTA = 0.001;

    @ParameterizedTest(name = "tan({0}) approximation with 5 terms")
    @DisplayName("Tangent approximation for valid inputs")
    @Tag("approximation")
    @ValueSource(doubles = {0, Math.PI/6, -Math.PI/4})
    void testTangentApproximation(double x) {
        assertEquals(Math.tan(x), TangentSeries.calculate(x, 5), DELTA);
    }

    @DisplayName("Increasing number of terms improves precision")
    @Tag("precision")
    @Test
    void testPrecisionImprovement() {
        double x = Math.PI/5;
        double result3terms = TangentSeries.calculate(x, 3);
        double result5terms = TangentSeries.calculate(x, 5);
        assertTrue(Math.abs(result5terms - Math.tan(x)) < Math.abs(result3terms - Math.tan(x)));
    }

    @DisplayName("Input near boundary should throw IllegalArgumentException")
    @Tag("exception")
    @Test
    void testBoundaryValue() {
        double x = Math.PI/2 - 0.09;
        assertThrows(
                IllegalArgumentException.class,
                () -> TangentSeries.calculate(x, 5)
        );
    }

    @DisplayName("Invalid terms count should throw ArrayIndexOutOfBoundsException")
    @Tag("exception")
    @Test
    void testInvalidTermsCount() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> TangentSeries.calculate(0.5, 6));
    }
}
