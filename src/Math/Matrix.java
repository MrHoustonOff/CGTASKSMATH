package Math;

import java.util.Arrays;

public class Matrix {
    private double[][] data;
    private int rows;
    private int cols;
    private static final float EPSILON = 1e-10f;

    /***
    Конструктор матрицы, принимает двумерный массив
     ***/
    public Matrix(double[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            throw new IllegalArgumentException("Матрица не может быть пустой.");
        }
        this.rows = m.length;
        this.cols = m[0].length;
        this.data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(m[i], 0, this.data[i], 0, cols);
        }
    }


    /***
    Вывод матрицы в консоль
     ***/
    public void print() {
        System.out.print("[");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j]);
                if (j < cols - 1) {
                    System.out.print(", ");
                }
            }
            if (i < rows - 1) {
                System.out.println();
                System.out.print(" ");
            }
        }
        System.out.print("]\n");
    }

    /***
    Возвращает значение элемента с индексом [row][col]
     ***/
    public double getAt(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Индекс вне границ матрицы.");
        }
        return data[row][col];
    }

    /***
    Изменяет значение элемента с индексом [row][col] на значение value
     ***/
    public void setAt(int row, int col, double value) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Индекс вне границ матрицы.");
        }
        data[row][col] = value;
    }

    /***
    Возвращает копию матрицы
     ***/
    public double[][] getData() {
        double[][] clone = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, clone[i], 0, cols);
        }
        return clone;
    }


    /***
    Возвращает число строк в матрице
     ***/
    public int getRows() {
        return rows;
    }

    /***
    Возвращает число столбцов в матрице
     ***/
    public int getCols() {
        return cols;
    }

    /***
    Создает нулевую матрицу
     ***/
    public Matrix zero() {
        return new Matrix(new double[rows][cols]);
    }

    /***
    Создает единичную матрицу
     ***/
    public Matrix one() {
        if (rows != cols) {
            throw new IllegalArgumentException("Матрица должна быть квадратной для создания единичной матрицы.");
        }
        double[][] oneData = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            oneData[i][i] = 1;
        }
        return new Matrix(oneData);
    }

    /***
    Транспонирует матрицу и возвращает её
     ***/
    public Matrix transposed() {
        double[][] transposed = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = data[i][j];
            }
        }
        return new Matrix(transposed);
    }

    /***
    Умножение матрицы на другую матрицу
     ***/
    public Matrix mul(Matrix other) {
        if (cols != other.getRows()) {
            throw new IllegalArgumentException("Число столбцов первой матрицы должно быть равно числу строк второй матрицы.");
        }
        double[][] result = new double[rows][other.cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < cols; k++) {
                    result[i][j] += data[i][k] * other.data[k][j];
                }
            }
        }
        return new Matrix(result);
    }

    /***
    Умножение матрицы на число
     ***/
    public Matrix mul(double n) {
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = data[i][j] * n;
            }
        }
        return new Matrix(result);
    }

    /***
    Умножение матрицы на вектор
     ***/
    public Vector mul(Vector v) {
        if (cols != v.getSize()) {
            throw new IllegalArgumentException("Размеры матрицы и вектора несовместимы для умножения.");
        }
        double[] result = new double[rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i] += data[i][j] * v.at(j);
            }
        }
        return new Vector(result);
    }

    /***
    Сложение матриц
     ***/
    public Matrix add(Matrix other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковые размеры для сложения.");
        }
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = data[i][j] + other.data[i][j];
            }
        }
        return new Matrix(result);
    }

    /***
    Вычитание матриц
     ***/
    public Matrix sub(Matrix other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковые размеры для вычитания.");
        }
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = data[i][j] - other.data[i][j];
            }
        }
        return new Matrix(result);
    }

    static void getCofactor(double[][] mat, double[][] temp,
                            int p, int q, int n)
    {
        int i = 0, j = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /***
    Определитель матрицы
     ***/
    public double determinant(double[][] mat, int n) {
        double D = 0;

        if (n == 1)
            return mat[0][0];

        double[][] temp = new double[rows][cols];

        int sign = 1;

        for (int f = 0; f < n; f++) {
            getCofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f] * determinant(temp, n - 1);
            sign = -sign;
        }

        return D;
    }

    /***
    Возвращает обратную матрицу
     ***/
    public Matrix inversion() {
        double[][] A = getData();
        int N = rows;

        if (cols != rows) {
            throw new IllegalArgumentException("Обратная матрица может быть найдена только для квадратной матрицы.");
        }

        if (determinant(A, N) == 0) {
            throw new IllegalArgumentException("Обратная матрица не может быть вычислена, так как определитель равен 0.");
        }

        double[][] E = new double[N][N];
        for (int i = 0; i < N; i++) {
            E[i][i] = 1.0; // Инициализация единичной матрицы
        }

        for (int k = 0; k < N; k++) {
            double temp = A[k][k];
            for (int j = 0; j < N; j++) {
                A[k][j] /= temp;
                E[k][j] /= temp;
            }

            for (int i = k + 1; i < N; i++) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int k = N - 1; k >= 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                double temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        return new Matrix(E); // Возвращаем обратную матрицу
    }

    public boolean equals(Matrix other) {
        if (this == other) return true;
        if (other == null || this.getRows() != other.getRows() || this.getCols() != other.getCols()) return false;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (Math.abs(data[i][j] - other.getData()[i][j]) >= EPSILON) {
                    return false;
                }
            }
        }
        return true;
    }
}
