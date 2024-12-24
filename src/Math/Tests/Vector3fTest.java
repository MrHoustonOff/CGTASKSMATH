package Math.Tests;

import Math.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector3fTest {
    private Vector3f vectorA;
    private Vector3f vectorB;

    @BeforeEach
    public void setUp() {
        vectorA = new Vector3f(new double[]{1.0, 2.0, 3.0});
        vectorB = new Vector3f(new double[]{4.0, 5.0, 6.0});
    }

    @Test
    public void testGetData() {
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, vectorA.getData());
    }

    @Test
    public void testGetX() {
        assertEquals(1.0, vectorA.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(2.0, vectorA.getY());
    }

    @Test
    public void testGetZ() {
        assertEquals(3.0, vectorA.getZ());
    }

    @Test
    public void testGetSize() {
        assertEquals(3, vectorA.getSize());
    }

    @Test
    public void testAt() {
        assertEquals(2.0, vectorA.at(1));
    }

    @Test
    public void testLength() {
        assertEquals(Math.sqrt(14), vectorA.length(), 1e-10);
    }

    @Test
    public void testAdd() {
        Vector3f result = vectorA.add(vectorB);
        assertArrayEquals(new double[]{5.0, 7.0, 9.0}, result.getData());
    }

    @Test
    public void testSub() {
        Vector3f result = vectorA.sub(vectorB);
        assertArrayEquals(new double[]{-3.0, -3.0, -3.0}, result.getData());
    }

    @Test
    public void testMul() {
        Vector3f result = vectorA.mul(2.0);
        assertArrayEquals(new double[]{2.0, 4.0, 6.0}, result.getData());
    }

    @Test
    public void testDiv() {
        Vector3f result = vectorA.div(2.0);
        assertArrayEquals(new double[]{0.5, 1.0, 1.5}, result.getData());
    }

    @Test
    public void testNormalize() {
        Vector3f result = vectorA.normalize();
        double length = vectorA.length();
        assertArrayEquals(new double[]{1.0 / length, 2.0 / length, 3.0 / length}, result.getData(), 1e-10);
    }

    @Test
    public void testDotProduct() {
        double result = vectorA.dotProduct(vectorB);
        assertEquals(32.0, result);
    }

    @Test
    public void testCrossProduct() {
        Vector3f result = vectorA.crossProduct(vectorB);
        assertArrayEquals(new double[]{-3.0, 6.0, -3.0}, result.getData());
    }

    @Test
    void testEquals_identicalVectors() {
        Vector3f v1 = new Vector3f(new double[]{1.0, 2.0, 3.0});
        Vector3f v2 = new Vector3f(new double[]{1.0, 2.0, 3.0});
        assertEquals(true, v1.equals(v2));
    }

    @Test
    void testEquals_differentVectors() {
        Vector3f v1 = new Vector3f(new double[]{1.0, 2.0, 3.0});
        Vector3f v2 = new Vector3f(new double[]{1.0, 2.0, 4.0});
        assertEquals(false, v1.equals(v2));
    }
}
