package Math.Tests;

import Math.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Matrix4X4Test {
    @Test
    void testConstructorDoubleArray() {
        double[][] m = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4X4 matrix = new Matrix4X4(m);
        assertArrayEquals(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}, matrix.getData());
    }

    @Test
    void testConstructorDoubleArrayIncorrectSize() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix4X4(new double[][]{{1, 2}, {3, 4}, {5, 6}}));
    }

    @Test
    void testGetAt() {
        double[][] m = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4X4 matrix = new Matrix4X4(m);
        assertEquals(1, matrix.getAt(0, 0));
        assertEquals(6, matrix.getAt(1, 1));
        assertEquals(16, matrix.getAt(3, 3));
    }

    @Test
    void testSetAt() {
        Matrix4X4 matrix = Matrix4X4.zero();
        matrix.setAt(1, 1, 5);
        assertEquals(5, matrix.getAt(1, 1));
    }

    @Test
    void testZero() {
        Matrix4X4 zeroMatrix = Matrix4X4.zero();
        assertArrayEquals(new double[4][4], zeroMatrix.getData());
    }

    @Test
    void testOne() {
        Matrix4X4 oneMatrix = Matrix4X4.one();
        assertArrayEquals(new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}, oneMatrix.getData(), String.valueOf(0.001));
    }

    @Test
    void testMulDouble() {
        double[][] m = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4X4 matrix = new Matrix4X4(m);
        Matrix4X4 result = matrix.mul(2);
        assertArrayEquals(new double[][]{{2, 4, 6, 8}, {10, 12, 14, 16}, {18, 20, 22, 24}, {26, 28, 30, 32}}, result.getData(), String.valueOf(0.001));
    }

    @Test
    void testMulVector4f() {
        double[][] m = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix4X4 matrix = new Matrix4X4(m);
        Vector4f v = new Vector4f(new double[]{1, 2, 3, 4});
        Vector4f result = matrix.mul(v);
        assertArrayEquals(new double[]{1, 2, 3, 4}, result.getData(), 0.001f);
    }

    @Test
    void testMulMatrix4X4() {
        double[][] m1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        double[][] m2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };
        Matrix4X4 matrix1 = new Matrix4X4(m1);
        Matrix4X4 matrix2 = new Matrix4X4(m2);
        Matrix4X4 result = matrix1.mul(matrix2);
        assertArrayEquals(new double[][]{
                {80, 70, 60, 50},
                {240, 214, 188, 162},
                {400, 358, 316, 274},
                {560, 502, 444, 386}
        }, result.getData(), String.valueOf(0.001));
    }


    @Test
    void testTransposed() {
        double[][] m = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4X4 matrix = new Matrix4X4(m);
        Matrix4X4 transposed = matrix.transposed();
        assertArrayEquals(new double[][]{{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 16}}, transposed.getData(), String.valueOf(0.001));
        assertNotSame(matrix, transposed); //Проверяем, что возвращается новая матрица
    }

    @Test
    void testAdd() {
        double[][] m1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        double[][] m2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };
        Matrix4X4 matrix1 = new Matrix4X4(m1);
        Matrix4X4 matrix2 = new Matrix4X4(m2);
        Matrix4X4 result = matrix1.add(matrix2);
        assertArrayEquals(new double[][]{{17, 17, 17, 17}, {17, 17, 17, 17}, {17, 17, 17, 17}, {17, 17, 17, 17}}, result.getData(), String.valueOf(0.001));
    }

    @Test
    void testSub() {
        double[][] m1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        double[][] m2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };
        Matrix4X4 matrix1 = new Matrix4X4(m1);
        Matrix4X4 matrix2 = new Matrix4X4(m2);
        Matrix4X4 result = matrix1.sub(matrix2);
        assertArrayEquals(new double[][]{{-15, -13, -11, -9}, {-7, -5, -3, -1}, {1, 3, 5, 7}, {9, 11, 13, 15}}, result.getData(), String.valueOf(0.001));
    }

    @Test
    void testDeterminant4x4() {
        double[][] m = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4X4 matrix = new Matrix4X4(m);
        assertEquals(0, matrix.determinant(), 0.001); // Используйте 0.001 для double
    }


    @Test
    void testDeterminant4x4_Identity() {
        double[][] m = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix4X4 matrix = new Matrix4X4(m);
        assertEquals(1, matrix.determinant(), 0.001);
    }

    @Test
    void testDeterminant4x4_AnotherExample() {
        double[][] m = {
                {1, 2, 0, 0},
                {3, 4, 0, 0},
                {0, 0, 1, 2},
                {0, 0, 3, 4}
        };
        Matrix4X4 matrix = new Matrix4X4(m);
        assertEquals(4, matrix.determinant(), 0.001);

    }

    @Test
    public void testInversionOfIdentityMatrix() {
        Matrix4X4 identityMatrix = new Matrix4X4(new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });

        Matrix4X4 invertedMatrix = identityMatrix.inversion();

        assertArrayEquals(identityMatrix.getData(), invertedMatrix.getData());
    }

    @Test
    public void testInversionOfSingularMatrix() {
        Matrix4X4 singularMatrix = new Matrix4X4(new double[][]{
                {1, 2, 3, 4},
                {2, 4, 6, 8},
                {3, 6, 9, 12},
                {4, 8, 12, 16}
        });

        Exception exception = assertThrows(IllegalArgumentException.class, singularMatrix::inversion);

        assertEquals("Обратная матрица не может быть вычислена, так как определитель равен 0.", exception.getMessage());
    }
}
