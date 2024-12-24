package Math.Tests;

import Math.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {
    @Test
    void testConstructorAndGetters() {
        double[] data = {1.0, 2.0, 3.0};
        Vector v = new Vector(data);
        assertArrayEquals(data, v.getData(), 0.001);
        assertEquals(3, v.getSize());
        assertEquals(1.0, v.at(0), 0.001);
        assertEquals(2.0, v.at(1), 0.001);
        assertEquals(3.0, v.at(2), 0.001);
    }


    @Test
    void testLength() {
        Vector v = new Vector(new double[]{3, 4});
        assertEquals(5.0, v.length(), 0.001);
        Vector v0 = new Vector(new double[]{0, 0});
        assertEquals(0.0, v0.length(), 0.001);
    }

    @Test
    void testAdd() {
        Vector v1 = new Vector(new double[]{1, 2});
        Vector v2 = new Vector(new double[]{3, 4});
        Vector v3 = v1.add(v2);
        assertArrayEquals(new double[]{4, 6}, v3.getData(), 0.001);
        // проверка на неизменяемость исходных векторов
        assertArrayEquals(new double[]{1, 2}, v1.getData(), 0.001);
        assertArrayEquals(new double[]{3, 4}, v2.getData(), 0.001);

    }

    @Test
    void testSub() {
        Vector v1 = new Vector(new double[]{5, 10});
        Vector v2 = new Vector(new double[]{3, 4});
        Vector v3 = v1.sub(v2);
        assertArrayEquals(new double[]{2, 6}, v3.getData(), 0.001);
        // проверка на неизменяемость исходных векторов
        assertArrayEquals(new double[]{5, 10}, v1.getData(), 0.001);
        assertArrayEquals(new double[]{3, 4}, v2.getData(), 0.001);

    }


    @Test
    void testMul() {
        Vector v = new Vector(new double[]{2, 4});
        Vector v2 = v.mul(3);
        assertArrayEquals(new double[]{6, 12}, v2.getData(), 0.001);
        // проверка на неизменяемость исходного вектора
        assertArrayEquals(new double[]{2, 4}, v.getData(), 0.001);

    }

    @Test
    public void testDivByPositiveScalar() {
        Vector vector = new Vector(new double[]{4.0, 2.0});
        Vector result = vector.div(2.0);

        assertArrayEquals(new double[]{2.0, 1.0}, result.getData());
    }

    @Test
    public void testDivByNegativeScalar() {
        Vector vector = new Vector(new double[]{4.0, 2.0});
        Vector result = vector.div(-2.0);

        assertArrayEquals(new double[]{-2.0, -1.0}, result.getData());
    }

    @Test
    public void testDivByOne() {
        Vector vector = new Vector(new double[]{4.0, 2.0});
        Vector result = vector.div(1.0);

        assertArrayEquals(vector.getData(), result.getData());
    }

    @Test
    public void testDivByZero() {
        Vector vector = new Vector(new double[]{4.0, 2.0});

        Exception exception = assertThrows(ArithmeticException.class, () -> {
            vector.div(0.0);
        });

        assertEquals("Деление на ноль!", exception.getMessage());
    }

    @Test
    public void testDivWithZeroVector() {
        Vector vector = new Vector(new double[]{0.0, 0.0});
        Vector result = vector.div(5.0);

        assertArrayEquals(new double[]{0.0, 0.0}, result.getData());
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(new double[]{3, 4});
        Vector normalized = v.normalize();
        assertEquals(1.0, normalized.length(), 0.001);
        assertArrayEquals(new double[]{0.6, 0.8}, normalized.getData(), 0.001);

        Vector v0 = new Vector(new double[]{0, 0});
        Vector normalized0 = v0.normalize();
        assertEquals(0.0, normalized0.length(), 0.001);
        assertArrayEquals(new double[]{0,0},normalized0.getData(), 0.001);

        // проверка на неизменяемость исходного вектора
        assertArrayEquals(new double[]{3, 4}, v.getData(), 0.001);

    }

    @Test
    void testSetAt(){
        Vector v = new Vector(new double[]{1,2,3});
        v.setAt(1, 10);
        assertArrayEquals(new double[]{1,10,3}, v.getData(), 0.001);
    }

    @Test
    void testAt(){
        Vector v = new Vector(new double[]{1,2,3});
        assertEquals(2, v.at(1), 0.001);
    }

    @Test
    public void testDotProductWithSameSizeVectors() {
        Vector vector1 = new Vector(new double[]{1, 2, 3});
        Vector vector2 = new Vector(new double[]{4, 5, 6});

        double result = vector1.dotProduct(vector2);

        assertEquals(32.0, result, 0.0001); // 1*4 + 2*5 + 3*6 = 32
    }

    @Test
    public void testDotProductWithZeroVectors() {
        Vector vector1 = new Vector(new double[]{0, 0, 0});
        Vector vector2 = new Vector(new double[]{0, 0, 0});

        double result = vector1.dotProduct(vector2);

        assertEquals(0.0, result, 0.0001); // Скалярное произведение нулевых векторов должно быть 0
    }

    @Test
    public void testDotProductWithNegativeValues() {
        Vector vector1 = new Vector(new double[]{-1, -2, -3});
        Vector vector2 = new Vector(new double[]{4, 5, 6});

        double result = vector1.dotProduct(vector2);

        assertEquals(-32.0, result, 0.0001); // -1*4 + -2*5 + -3*6 = -32
    }

    @Test
    public void testDotProductWithDifferentSizeVectors() {
        Vector vector1 = new Vector(new double[]{1, 2});
        Vector vector2 = new Vector(new double[]{3, 4, 5});

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            vector1.dotProduct(vector2);
        });

        assertEquals("Векторы должны быть одинаковой размерности для скалярного произведения.", exception.getMessage());
    }

    @Test
    void testEquals_identicalVectors() {
        Vector v1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector v2 = new Vector(new double[]{1.0, 2.0, 3.0});
        assertEquals(true, v1.equals(v2));
    }

    @Test
    void testEquals_differentVectors() {
        Vector v1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector v2 = new Vector(new double[]{1.0, 2.0, 4.0});
        assertEquals(false, v1.equals(v2));
    }

    @Test
    void testEquals_differentSizes() {
        Vector v1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector v2 = new Vector(new double[]{1.0, 2.0});
        assertEquals(false, v1.equals(v2));
    }

    @Test
    void testEquals_nullVector() {
        Vector v1 = new Vector(new double[]{1.0, 2.0, 3.0});
        assertEquals(false, v1.equals(null));
    }

    @Test
    void testEquals_almostEqualVectors() {
        Vector v1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector v2 = new Vector(new double[]{1.0, 2.0, 3.000000000001});
        assertEquals(true, v1.equals(v2)); // Из-за EPSILON
    }

    @Test
    void testGetData_returnsCopy() {
        Vector v1 = new Vector(new double[]{1.0, 2.0, 3.0});
        double[] data = v1.getData();
        data[0] = 100.0; // Изменение копии не должно влиять на оригинал
        assertNotEquals(100.0, v1.getData()[0]);
    }

    @Test
    void testGetSize(){
        Vector v1 = new Vector(new double[]{1.0, 2.0, 3.0});
        assertEquals(3, v1.getSize());
    }
}
