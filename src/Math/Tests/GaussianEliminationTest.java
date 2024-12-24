package Math.Tests;

import Math.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GaussianEliminationTest {

    @Test
    void testSolveWithUniqueSolution() {
        double[][] matrix = {
                {2, 1, -1},
                {-3, -1, 2},
                {-2, 1, 2}
        };
        double[] constants = {8, -11, -3};
        double[] expectedSolution = {2, 3, -1};

        double[] solution = GaussianElimination.solve(matrix, constants);

        assertArrayEquals(expectedSolution, solution, 1e-10, "Решение должно быть корректным для заданной системы.");
    }

    @Test
    void testSolveWithNoSolution() {
        double[][] matrix = {
                {1, 1},
                {2, 2}
        };
        double[] constants = {5, 10};

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            GaussianElimination.solve(matrix, constants);
        });

        assertTrue(exception.getMessage().contains("Система не имеет единственного решения"),
                "Сообщение об ошибке должно указать на отсутствие решения.");
    }

    @Test
    void testSolveWithInfiniteSolutions() {
        double[][] matrix = {
                {1, 2, -1},
                {2, 4, -2},
                {3, 6, -3}
        };
        double[] constants = {3, 6, 9};

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            GaussianElimination.solve(matrix, constants);
        });

        assertTrue(exception.getMessage().contains("Система не имеет единственного решения"),
                "Сообщение об ошибке должно указать на отсутствие решения.");
    }

    @Test
    void testSolveWithDegenerateMatrix() {
        double[][] matrix = {
                {0, 0},
                {0, 0}
        };
        double[] constants = {0, 0};

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            GaussianElimination.solve(matrix, constants);
        });

        assertTrue(exception.getMessage().contains("Система не имеет единственного решения"),
                "Сообщение об ошибке должно указать на вырожденность матрицы.");
    }

    @Test
    void testSolveWithLargeMatrix() {
        double[][] matrix = {
                {3, -0.1, -0.2},
                {0.1, 7, -0.3},
                {0.3, -0.2, 10}
        };
        double[] constants = {7.85, -19.3, 71.4};
        double[] expectedSolution = {3, -2.5, 7};

        double[] solution = GaussianElimination.solve(matrix, constants);

        assertArrayEquals(expectedSolution, solution, 1e-10, "Решение должно быть корректным для большой матрицы.");
    }
}
