package Math;

public class Matrix3X3 {
    private Matrix matrix;

    /***
    Конструктор матрицы, принимает двумерный массив
     ***/
    public Matrix3X3(double[][] data) {
        if (data.length != 3 || data[0].length != 3) {
            throw new IllegalArgumentException("Матрица должна быть 3x3");
        }
        this.matrix = new Matrix(data);
    }

    /***
    Возвращает значение элемента с индексом [row][col]
     ***/
    public double getAt(int row, int col) {
        return matrix.getAt(row, col);
    }

    /***
    Изменяет значение элемента с индексом [row][col] на значение value
     ***/
    public void setAt(int row, int col, float value) {
        matrix.setAt(row, col, value);
    }

    /***
    Возвращает копию матрицы
     ***/
    public double[][] getData() {
        return matrix.getData();
    }

    /***
    Создает пустую матрицу
     ***/
    public Matrix3X3() {
        this(new double[3][3]);
    }

    /***
    Создает нулевую матрицу
     ***/
    public static Matrix3X3 zero() {
        return new Matrix3X3();
    }

    /***
    Создает единичную матрицу
     ***/
    public static Matrix3X3 one() {
        double[][] oneData = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        return new Matrix3X3(oneData);
    }

    /***
    Умножение матрицы на другую матрицу
     ***/
    public Matrix3X3 mul(Matrix3X3 other) {
        return new Matrix3X3(matrix.mul(other.matrix).getData());
    }

    /***
    Умножение матрицы на число
     ***/
    public Matrix3X3 mul(double n) {
        return new Matrix3X3(matrix.mul(n).getData());
    }

    /***
    Умножение матрицы на вектор
     ***/
    public Vector3f mul(Vector3f v) {
        return new Vector3f(matrix.mul(v.vector).getData());
    }

    /***
    Вывод матрицы в консоль
     ***/
    public void print() {
        matrix.print();
    }

    /***
    Транспонирует матрицу и возвращает её
     ***/
    public Matrix3X3 transposed() {
        return new Matrix3X3(matrix.transposed().getData());
    }

    /***
    Сложение матриц
     ***/
    public Matrix3X3 add(Matrix3X3 other) {
        return new Matrix3X3(matrix.add(other.matrix).getData());
    }

    /***
    Вычитание матриц
     ***/
    public Matrix3X3 sub(Matrix3X3 other) {
        return new Matrix3X3(matrix.sub(other.matrix).getData());
    }

    /***
    Определитель матрицы
     ***/
    public double determinant() {
        double[][] d = matrix.getData();
        return d[0][0] * (d[1][1] * d[2][2] - d[1][2] * d[2][1]) -
                d[0][1] * (d[1][0] * d[2][2] - d[1][2] * d[2][0]) +
                d[0][2] * (d[1][0] * d[2][1] - d[1][1] * d[2][0]);
    }

    /***
    Возвращает обратную матрицу
     ***/
    public Matrix3X3 inverse() {
        double[][] d = matrix.getData();
        double det = determinant();

        if (det == 0) {
            throw new ArithmeticException("Определитель равен нулю. Обратной матрицы не существует.");
        }

        double[][] inverseData = new double[3][3];

        inverseData[0][0] = (d[1][1] * d[2][2] - d[1][2] * d[2][1]) / det;
        inverseData[0][1] = (d[0][2] * d[2][1] - d[0][1] * d[2][2]) / det;
        inverseData[0][2] = (d[0][1] * d[1][2] - d[0][2] * d[1][1]) / det;
        inverseData[1][0] = (d[1][2] * d[2][0] - d[1][0] * d[2][2]) / det;
        inverseData[1][1] = (d[0][0] * d[2][2] - d[0][2] * d[2][0]) / det;
        inverseData[1][2] = (d[0][2] * d[1][0] - d[0][0] * d[1][2]) / det;
        inverseData[2][0] = (d[1][0] * d[2][1] - d[1][1] * d[2][0]) / det;
        inverseData[2][1] = (d[0][1] * d[2][0] - d[0][0] * d[2][1]) / det;
        inverseData[2][2] = (d[0][0] * d[1][1] - d[0][1] * d[1][0]) / det;

        return new Matrix3X3(inverseData);
    }
}
