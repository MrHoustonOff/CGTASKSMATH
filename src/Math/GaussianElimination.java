package Math;

public class GaussianElimination {

    /**
     * Решает систему линейных уравнений методом Гаусса.
     * @param matrix матрица коэффициентов системы уравнений.
     * @param constants вектор свободных членов.
     * @return массив, представляющий решение системы уравнений.
     * @throws IllegalArgumentException если матрица и вектор имеют несовместимые размеры
     *                                  или если система не имеет единственного решения.
     */
    public static double[] solve(double[][] matrix, double[] constants) {
        int n = matrix.length;

        if (constants.length != n) {
            throw new IllegalArgumentException("Размер матрицы и вектора свободных членов несовместимы.");
        }

        // Расширенная матрица
        double[][] augmentedMatrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmentedMatrix[i], 0, n);
            augmentedMatrix[i][n] = constants[i];
        }

        // Прямой ход метода Гаусса
        for (int i = 0; i < n; i++) {
            // Поиск максимального элемента в текущем столбце для избежания деления на ноль
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(augmentedMatrix[k][i]) > Math.abs(augmentedMatrix[maxRow][i])) {
                    maxRow = k;
                }
            }

            // Поменять местами строки
            double[] temp = augmentedMatrix[i];
            augmentedMatrix[i] = augmentedMatrix[maxRow];
            augmentedMatrix[maxRow] = temp;

            // Проверка на вырожденность матрицы
            if (Math.abs(augmentedMatrix[i][i]) < 1e-10) {
                throw new IllegalArgumentException("Система не имеет единственного решения (вырожденная матрица).");
            }

            // Приведение текущей строки к ступенчатому виду
            for (int k = i + 1; k < n; k++) {
                double factor = augmentedMatrix[k][i] / augmentedMatrix[i][i];
                for (int j = i; j <= n; j++) {
                    augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j];
                }
            }
        }

        // Обратный ход метода Гаусса
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = augmentedMatrix[i][n] / augmentedMatrix[i][i];
            for (int k = i - 1; k >= 0; k--) {
                augmentedMatrix[k][n] -= augmentedMatrix[k][i] * solution[i];
            }
        }

        return solution;
    }
}
