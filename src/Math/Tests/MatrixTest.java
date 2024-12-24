package Math.Tests;

import Math.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    @Test
    void testConstructorDoubleArray() {
        double[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix matrix = new Matrix(m);
        assertArrayEquals(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, matrix.getData());
    }

    @Test
    public void testNullMatrix() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Matrix(null);
        });
        assertEquals("Матрица не может быть пустой.", exception.getMessage());
    }

    @Test
    public void testEmptyMatrix() {
        double[][] input = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Matrix(input);
        });
        assertEquals("Матрица не может быть пустой.", exception.getMessage());
    }

    @Test
    public void testMatrixWithEmptyRow() {
        double[][] input = {
                {}
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Matrix(input);
        });
        assertEquals("Матрица не может быть пустой.", exception.getMessage());
    }

    @Test
    public void testGetAtOutOfBounds() {
        double[][] input = {
                {1.0, 2.0},
                {3.0, 4.0}
        };
        Matrix matrix = new Matrix(input);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            matrix.getAt(2, 0);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            matrix.getAt(0, 2);
        });
    }

    @Test
    public void testSetAt() {
        double[][] input = {
                {1.0, 2.0},
                {3.0, 4.0}
        };
        Matrix matrix = new Matrix(input);
        matrix.setAt(0, 1, 5.0);
        assertEquals(5.0, matrix.getAt(0, 1));
    }

    @Test
    public void testSetAtOutOfBounds() {
        double[][] input = {
                {1.0, 2.0},
                {3.0, 4.0}
        };
        Matrix matrix = new Matrix(input);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            matrix.setAt(2, 0, 5.0);
        });
    }

    @Test
    void testTransposed() {
        double[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix matrix = new Matrix(m);
        Matrix transposed = matrix.transposed();
        assertArrayEquals(new double[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}}, transposed.getData(), String.valueOf(0.001));
        assertNotSame(matrix, transposed);

    }

    @Test
    public void testMul() {
        double[][] inputA = {
                {1.0, 2.0},
                {3.0, 4.0}
        };

        double[][] inputB = {
                {5.0, 6.0},
                {7.0, 8.0}
        };

        Matrix matrixA = new Matrix(inputA);
        Matrix matrixB = new Matrix(inputB);

        Matrix result = matrixA.mul(matrixB);

        assertEquals(19.0, result.getAt(0, 0)); // (1*5 + 2*7)
        assertEquals(22.0, result.getAt(0, 1)); // (1*6 + 2*8)
        assertEquals(43.0, result.getAt(1, 0)); // (3*5 + 4*7)
        assertEquals(50.0, result.getAt(1, 1)); // (3*6 + 4*8)
    }

    @Test
    public void testMulWithScalar() {
        double[][] input = {
                {1.0, 2.0},
                {3.0, 4.0}
        };

        Matrix matrix = new Matrix(input);

        Matrix result = matrix.mul(2);

        assertEquals(2.0, result.getAt(0, 0));
        assertEquals(4.0, result.getAt(0, 1));
        assertEquals(6.0, result.getAt(1, 0));
        assertEquals(8.0, result.getAt(1, 1));
    }

    @Test
    void mul_compatibleDimensions_returnsCorrectResult() {
        double[][] matrixData = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[] vectorData = {1, 2, 3};
        Vector v = new Vector(vectorData);

        Matrix matrix = new Matrix(matrixData);
        Vector result = matrix.mul(v);
        double[] expectedResult = {14, 32, 50};
        assertArrayEquals(expectedResult, result.getData(), 1e-9);
    }

    @Test
    public void testOne() {
        double[][] input = {
                {1.0, 2.0},
                {3.0, 4.0}
        };

        Matrix matrix = new Matrix(input);

        Matrix identityMatrix = matrix.one();

        assertEquals(1.0, identityMatrix.getAt(0, 0));
        assertEquals(1.0, identityMatrix.getAt(1, 1));
    }

    @Test
    public void testZero() {
        double[][] input = {
                {1.0, 2.0},
                {3.0, 4.0}
        };

        Matrix matrix = new Matrix(input);

        Matrix zeroMatrix = matrix.zero();

        assertEquals(0.0, zeroMatrix.getAt(0, 0));
        assertEquals(0.0, zeroMatrix.getAt(1, 1));
    }

    @Test
    public void testMulWithCompatibleVector() {
        double[][] matrixData = {
                {1.0, 2.0},
                {3.0, 4.0}
        };
        Matrix matrix = new Matrix(matrixData);
        Vector vector = new Vector(new double[]{5.0, 6.0});

        Vector result = matrix.mul(vector);

        assertEquals(17.0, result.getData()[0]); // 1*5 + 2*6
        assertEquals(39.0, result.getData()[1]); // 3*5 + 4*6
    }

    @Test
    public void testMulWithIncompatibleVector() {
        double[][] matrixData = {
                {1.0, 2.0},
                {3.0, 4.0}
        };
        Matrix matrix = new Matrix(matrixData);
        Vector vector = new Vector(new double[]{5.0}); // Несоответствующий размер

        assertThrows(IllegalArgumentException.class, () -> {
            matrix.mul(vector);
        });
    }

    @Test
    public void testAddWithCompatibleMatrix() {
        double[][] matrixDataA = {
                {1.0, 2.0},
                {3.0, 4.0}
        };
        double[][] matrixDataB = {
                {5.0, 6.0},
                {7.0, 8.0}
        };

        Matrix matrixA = new Matrix(matrixDataA);
        Matrix matrixB = new Matrix(matrixDataB);

        Matrix result = matrixA.add(matrixB);

        assertEquals(6.0, result.getAt(0, 0)); // 1 + 5
        assertEquals(8.0, result.getAt(0, 1)); // 2 + 6
        assertEquals(10.0, result.getAt(1, 0)); // 3 + 7
        assertEquals(12.0, result.getAt(1, 1)); // 4 + 8
    }

    @Test
    public void testAddWithIncompatibleMatrix() {
        double[][] matrixDataA = {
                {1.0, 2.0},
                {3.0, 4.0}
        };
        double[][] matrixDataB = {
                {5.0, 6.0}
        }; // Несоответствующий размер

        Matrix matrixA = new Matrix(matrixDataA);
        Matrix matrixB = new Matrix(matrixDataB);

        assertThrows(IllegalArgumentException.class, () -> {
            matrixA.add(matrixB);
        });
    }

    @Test
    public void testSubWithCompatibleMatrix() {
        double[][] matrixDataA = {
                {5.0, 6.0},
                {7.0, 8.0}
        };
        double[][] matrixDataB = {
                {1.0, 2.0},
                {3.0, 4.0}
        };

        Matrix matrixA = new Matrix(matrixDataA);
        Matrix matrixB = new Matrix(matrixDataB);

        Matrix result = matrixA.sub(matrixB);

        assertEquals(4.0, result.getAt(0, 0)); // 5 - 1
        assertEquals(4.0, result.getAt(0, 1)); // 6 - 2
        assertEquals(4.0, result.getAt(1, 0)); // 7 - 3
        assertEquals(4.0, result.getAt(1, 1)); // 8 - 4
    }

    @Test
    public void testSubWithIncompatibleMatrix() {
        double[][] matrixDataA = {
                {5.0, 6.0},
                {7.0, 8.0}
        };
        double[][] matrixDataB = {
                {1.0, 2.0}
        }; // Несоответствующий размер

        Matrix matrixA = new Matrix(matrixDataA);
        Matrix matrixB = new Matrix(matrixDataB);

        assertThrows(IllegalArgumentException.class, () -> {
            matrixA.sub(matrixB);
        });
    }

    @Test
    void testInversion_SingularMatrix() {
        double[][] singularMatrix = {{1, 2}, {2, 4}};
        Matrix matrix = new Matrix(singularMatrix);
        assertThrows(IllegalArgumentException.class, matrix::inversion);
    }
    @Test
    void testInversion_NonSquareMatrix() {
        double[][] nonSquareMatrix = {{1, 2}, {3, 4}, {5, 6}};
        Matrix matrix = new Matrix(nonSquareMatrix);
        assertThrows(IllegalArgumentException.class, matrix::inversion);
    }

    @Test
    void testInversion_ValidInput() {
        double[][] m = {{1, 2}, {3, 4}};
        Matrix matrix = new Matrix(m);
        Matrix inversion = matrix.inversion();
        assertArrayEquals(new double[][]{{-2, 1}, {1.5, -0.5}}, inversion.getData(), String.valueOf(0.001));
        assertNotSame(matrix, inversion);

    }

    @Test
    void testEquals_identicalMatrices() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Matrix m1 = new Matrix(data);
        Matrix m2 = new Matrix(data);
        assertEquals(true, m2.equals(m1));
    }

    @Test
    void testEquals_differentMatrices() {
        Matrix m1 = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        Matrix m2 = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 5.0}});
        assertNotEquals(m1, m2);
    }

    @Test
    void testEquals_differentDimensions() {
        Matrix m1 = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        Matrix m2 = new Matrix(new double[][]{{1.0, 2.0}});
        assertNotEquals(m1, m2);
    }

    @Test
    void testEquals_nullMatrix() {
        Matrix m1 = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        assertNotEquals(m1, null);
    }

    @Test
    void testEquals_almostEqualMatrices() {
        Matrix m1 = new Matrix(new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        Matrix m2 = new Matrix(new double[][]{{1.0, 2.00000000001}, {3.0, 4.0}});
        assertEquals(m1.equals(m2), true); // из-за EPSILON
    }

    @Test
    void testGetData_returnsCopy() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Matrix m1 = new Matrix(data);
        double[][] copiedData = m1.getData();
        copiedData[0][0] = 100.0; // Изменение копии не должно влиять на оригинал
        assertNotEquals(100.0, m1.getData()[0][0]);
    }
}
