package org.example;

public class TangentSeries {
    private static final double MAX_ALLOWED_X = Math.PI / 2 - 0.1;

    public static double calculate(double x, int terms) {
        if (Math.abs(x) >= MAX_ALLOWED_X) {
            throw new IllegalArgumentException("x must be in (-π/2, π/2)");
        }

        double result = 0;
        double[] coefficients = {1.0, 1.0/3, 2.0/15, 17.0/315, 62.0/2835};

        for (int i = 0; i < terms; i++) {
            int power = 2*i + 1;
            result += coefficients[i] * Math.pow(x, power);
        }
        return result;
    }
}