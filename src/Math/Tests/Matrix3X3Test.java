package Math.Tests;

import Math.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Matrix3X3Test {
    @Test
    void constructor_validData_createsMatrix() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3X3 matrix = new Matrix3X3(data);
        assertArrayEquals(data, matrix.getData());
    }

    @Test
    void constructor_invalidData_throwsIllegalArgumentException() {
        double[][] data = {{1, 2}, {3, 4}};
        assertThrows(IllegalArgumentException.class, () -> new Matrix3X3(data));
    }

    @Test
    void getAt_validIndices_returnsCorrectValue() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3X3 matrix = new Matrix3X3(data);
        assertEquals(5, matrix.getAt(1, 1));
    }


    @Test
    void setAt_validIndices_setsCorrectValue() {
        Matrix3X3 matrix = new Matrix3X3();
        matrix.setAt(0, 0, 10);
        assertEquals(10, matrix.getAt(0,0));
    }


    @Test
    void zero_createsZeroMatrix() {
        Matrix3X3 zeroMatrix = Matrix3X3.zero();
        double[][] expectedData = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertArrayEquals(expectedData, zeroMatrix.getData());
    }


    @Test
    void one_createsIdentityMatrix() {
        Matrix3X3 identityMatrix = Matrix3X3.one();
        double[][] expectedData = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        assertArrayEquals(expectedData, identityMatrix.getData());
    }

    @Test
    void mul_matrix_multipliesMatricesCorrectly() {
        double[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        Matrix3X3 m1 = new Matrix3X3(data1);
        Matrix3X3 m2 = new Matrix3X3(data2);
        Matrix3X3 result = m1.mul(m2);

        double[][] expectedData = {{30, 24, 18}, {84, 69, 54}, {138, 114, 90}};
        assertArrayEquals(expectedData, result.getData());
    }


    @Test
    void mul_scalar_multipliesByScalarCorrectly() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3X3 m = new Matrix3X3(data);
        Matrix3X3 result = m.mul(2);
        double[][] expectedData = {{2, 4, 6}, {8, 10, 12}, {14, 16, 18}};
        assertArrayEquals(expectedData, result.getData());

    }

    @Test
    void mul_compatibleDimensions_returnsCorrectResult() {
        double[][] matrixData = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[] vectorData = {1, 2, 3};
        Vector3f v = new Vector3f(vectorData);

        Matrix3X3 matrix = new Matrix3X3(matrixData);
        Vector3f result = matrix.mul(v);
        double[] expectedResult = {14, 32, 50};
        assertArrayEquals(expectedResult, result.getData(), 1e-9);
    }

    @Test
    void transposed_transposesMatrixCorrectly() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3X3 m = new Matrix3X3(data);
        Matrix3X3 transposed = m.transposed();
        double[][] expectedData = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        assertArrayEquals(expectedData, transposed.getData());

    }

    @Test
    void determinant_calculatesDeterminantCorrectly() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 10}};
        Matrix3X3 m = new Matrix3X3(data);
        double det = m.determinant();
        assertEquals(-3, det, 1e-9);
    }

    @Test
    void inverse_calculatesInverseCorrectly() {
        double[][] data = {{1, 2, 3}, {0, 1, 4}, {5, 6, 0}};
        Matrix3X3 m = new Matrix3X3(data);
        Matrix3X3 inverse = m.inverse();
        double[][] expectedData = {{-24, 18, 5}, {20, -15, -4}, {-5, 4, 1}};

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                assertEquals(expectedData[i][j], inverse.getData()[i][j], 1e-9);

    }


    @Test
    void inverse_zeroDeterminant_throwsArithmeticException() {
        double[][] data = {{1, 2, 3}, {2, 4, 6}, {7, 8, 9}};
        Matrix3X3 m = new Matrix3X3(data);
        assertThrows(ArithmeticException.class, m::inverse);
    }



    @Test
    void add_addsMatricesCorrectly() {
        Matrix3X3 m1 = new Matrix3X3(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix3X3 m2 = new Matrix3X3(new double[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}});
        Matrix3X3 result = m1.add(m2);
        assertArrayEquals(new double[][]{{10, 10, 10}, {10, 10, 10}, {10, 10, 10}}, result.getData());
    }

    @Test
    void sub_subtractsMatricesCorrectly() {
        Matrix3X3 m1 = new Matrix3X3(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix3X3 m2 = new Matrix3X3(new double[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        Matrix3X3 result = m1.sub(m2);
        assertArrayEquals(new double[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}}, result.getData()); // Corrected expected result
    }
}
