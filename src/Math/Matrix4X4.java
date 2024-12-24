package Math;

public class Matrix4X4 {
    private Matrix matrix;

    /***
    Конструктор матрицы, принимает двумерный массив
     ***/
    public Matrix4X4(double[][] data) {
        if (data.length != 4 || data[0].length != 4) {
            throw new IllegalArgumentException("Матрица должна быть 3X3");
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
    public Matrix4X4() {
        this(new double[4][4]);
    }

    /***
    Создает нулевую матрицу
     ***/
    public static Matrix4X4 zero() {
        return new Matrix4X4();
    }

    /***
    Создает единичную матрицу
     ***/
    public static Matrix4X4 one() {
        double[][] oneData = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        return new Matrix4X4(oneData);
    }

    /***
    Умножение матрицы на другую матрицу
     ***/
    public Matrix4X4 mul(Matrix4X4 other) {
        return new Matrix4X4(matrix.mul(other.matrix).getData());
    }

    /***
    Умножение матрицы на число
     ***/
    public Matrix4X4 mul(double n) {
        return new Matrix4X4(matrix.mul(n).getData());
    }

    /***
    Умножение матрицы на вектор
     ***/
    public Vector4f mul(Vector4f v) {
        return new Vector4f(matrix.mul(v.vector).getData());
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
    public Matrix4X4 transposed() {
        return new Matrix4X4(matrix.transposed().getData());
    }

    /***
    Сложение матриц
     ***/
    public Matrix4X4 add(Matrix4X4 other) {
        return new Matrix4X4(matrix.add(other.matrix).getData());
    }

    /***
    Вычитание матриц
     ***/
    public Matrix4X4 sub(Matrix4X4 other) {
        return new Matrix4X4(matrix.sub(other.matrix).getData());
    }

    /***
    Определитель матрицы
     ***/
    public double determinant() {
        return matrix.determinant(matrix.getData(), 4);
    }

    /***
    Возвращает обратную матрицу
     ***/
    public Matrix4X4 inversion() {
        return new Matrix4X4(matrix.inversion().getData());
    }

}
