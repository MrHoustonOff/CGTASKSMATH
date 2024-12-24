package Math.Tests;

import Math.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2fTest {
    @Test
    void testConstructor() {
        Vector2f v = new Vector2f(new double[]{1.0, 2.0});
        assertArrayEquals(new double[]{1.0, 2.0}, v.getData());
    }

    @Test
    void testGetX() {
        Vector2f v = new Vector2f(new double[]{3.0, 4.0});
        assertEquals(3.0, v.getX());
    }

    @Test
    void testGetY() {
        Vector2f v = new Vector2f(new double[]{3.0, 4.0});
        assertEquals(4.0, v.getY());
    }

    @Test
    void testAt() {
        Vector2f v = new Vector2f(new double[]{5.0, 6.0});
        assertEquals(5.0, v.at(0));
        assertEquals(6.0, v.at(1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> v.at(2)); //Проверяем обработку исключения
    }

    @Test
    void testLength() {
        Vector2f v = new Vector2f(new double[]{3.0, 4.0});
        assertEquals(5.0, v.length(), 0.001); // Используем delta для сравнения вещественных чисел
        Vector2f zeroVector = new Vector2f(new double[]{0, 0});
        assertEquals(0, zeroVector.length(),0.001);
    }

    @Test
    void testAdd() {
        Vector2f v1 = new Vector2f(new double[]{1.0, 2.0});
        Vector2f v2 = new Vector2f(new double[]{3.0, 4.0});
        Vector2f result = v1.add(v2);
        assertArrayEquals(new double[]{4.0, 6.0}, result.getData());
    }

    @Test
    void testSub() {
        Vector2f v1 = new Vector2f(new double[]{5.0, 6.0});
        Vector2f v2 = new Vector2f(new double[]{3.0, 4.0});
        Vector2f result = v1.sub(v2);
        assertArrayEquals(new double[]{2.0, 2.0}, result.getData());
    }

    @Test
    void testMultiply() {
        Vector2f v = new Vector2f(new double[]{2.0, 3.0});
        Vector2f result = v.mul(4.0);
        assertArrayEquals(new double[]{8.0, 12.0}, result.getData());
    }

    @Test
    public void testDivByPositiveScalar() {
        Vector2f vector = new Vector2f(new double[]{4.0, 2.0});
        Vector2f result = vector.div(2.0);
        assertArrayEquals(new double[]{2.0, 1.0}, result.getData());
    }

    @Test
    public void testDivByNegativeScalar() {
        Vector2f vector = new Vector2f(new double[]{4.0, 2.0});
        Vector2f result = vector.div(-2.0);

        assertArrayEquals(new double[]{-2.0, -1.0}, result.getData());
    }

    @Test
    public void testDivByOne() {
        Vector2f vector = new Vector2f(new double[]{4.0, 2.0});
        Vector2f result = vector.div(1.0);

        assertArrayEquals(vector.getData(), result.getData());
    }

    @Test
    public void testDivByZero() {
        Vector2f vector = new Vector2f(new double[]{4.0, 2.0});

        Exception exception = assertThrows(ArithmeticException.class, () -> {
            vector.div(0.0);
        });

        assertEquals("Деление на ноль!", exception.getMessage());
    }

    @Test
    public void testDivWithZeroVector() {
        Vector2f vector = new Vector2f(new double[]{0.0, 0.0});
        Vector2f result = vector.div(5.0);

        assertArrayEquals(new double[]{0.0, 0.0}, result.getData());
    }

    @Test
    void testNormalize() {
        Vector2f v = new Vector2f(new double[]{3.0, 4.0});
        Vector2f normalized = v.normalize();
        assertEquals(1.0, normalized.length(), 0.001); // Длина нормированного вектора равна 1
        Vector2f zeroVector = new Vector2f(new double[]{0, 0});
        Vector2f normalizedZero = zeroVector.normalize();
        assertArrayEquals(new double[]{0,0}, normalizedZero.getData());
    }

    @Test
    void testDotProduct() {
        Vector2f v1 = new Vector2f(new double[]{1.0, 2.0});
        Vector2f v2 = new Vector2f(new double[]{3.0, 4.0});
        assertEquals(11.0, v1.dotProduct(v2));
    }

    @Test
    void testEquals_identicalVectors() {
        Vector2f v1 = new Vector2f(new double[]{1.0, 2.0, 3.0});
        Vector2f v2 = new Vector2f(new double[]{1.0, 2.0, 3.0});
        assertEquals(true, v1.equals(v2));
    }

    @Test
    void testEquals_differentVectors() {
        Vector2f v1 = new Vector2f(new double[]{1.0, 2.0, 3.0});
        Vector2f v2 = new Vector2f(new double[]{1.0, 2.0, 4.0});
        assertEquals(false, v1.equals(v2));
    }
}
