package Math;

public class Vector {
    private double[] data;

    /***
    Конструктор вектора, принимает одномерный массив
     ***/
    public Vector(double[] data) {
        this.data = data.clone();
    }

    /***
    Возвращает копию вектора
     ***/
    public double[] getData() {
        double[] deepCopy = new double[data.length];
        System.arraycopy(data, 0, deepCopy, 0, data.length);
        return deepCopy;
    }

    /***
    Возвращает размерность вектора
     ***/
    public int getSize(){
        return data.length;
    }

    /***
    Возвращает значение элемента вектора под индексом idx
     ***/
    public double at(int idx) {
        if (idx < 0 || idx >= data.length) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + idx);
        }
        return data[idx];
    }

    /***
    Изменяет значение элемента вектор под индексом idx на значение value
     ***/
    public void setAt(int idx, double value) {
        if (idx < 0 || idx >= data.length) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + idx);
        }
        data[idx] = value;
    }
    
    private static final float EPSILON = 1e-10f;

    /***
    Возвращает длину вектора
     ***/
    public double length() {
        double sumOfSquares = 0;
        for (double element : data) {
            sumOfSquares += element * element;
        }
        return Math.sqrt(sumOfSquares);
    }

    /***
    Вывод вектора в консоль
     ***/
    public void print() {
        if (data.length == 0) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < data.length - 1; i++) {
            System.out.printf("%s, \n", data[i]);

        }
        System.out.print(data[data.length - 1] + "]\n");
    }

    /***
    Сложение векторов
     ***/
    public Vector add(Vector other) {
        if (this.getSize() != other.getSize()) {
            throw new IllegalArgumentException("Векторы должны быть одинаковой размерности для сложения.");
        }
        double[] resultData = new double[getSize()];
        for (int i = 0; i < getSize(); i++) {
            resultData[i] = data[i] + other.getData()[i];
        }
        return new Vector(resultData);
    }

    /***
    Вычитание векторов
     ***/
    public Vector sub(Vector other) {
        if (this.getSize() != other.getSize()) {
            throw new IllegalArgumentException("Векторы должны быть одинаковой размерности для вычитания.");
        }
        double[] resultData = new double[getSize()];
        for (int i = 0; i < getSize(); i++) {
            resultData[i] = data[i] - other.getData()[i];
        }
        return new Vector(resultData);
    }

    /***
    Умножение вектора на число
     ***/
    public Vector mul(double scalar) {
        double[] resultData = new double[getSize()];
        for (int i = 0; i < getSize(); i++) {
            resultData[i] = data[i] * scalar;
        }
        return new Vector(resultData);
    }

    /***
    Деление вектора на число
     ***/
    public Vector div(double scalar) {
        if (Math.abs(scalar) < EPSILON) {
            throw new ArithmeticException("Деление на ноль!");
        }
        return mul(1.0 / scalar);
    }

    /***
    Нормализация вектора
     ***/
    public Vector normalize() {
        double magnitude = length();
        if (magnitude == 0) {
            return new Vector(new double[data.length]);
        }
        double[] normalized = new double[data.length];
        for (int i = 0; i < normalized.length; i++) {
            normalized[i] = data[i] / magnitude;
        }
        return new Vector(normalized);
    }


    /***
    Скалярное произведение векторов
     ***/
    public double dotProduct(Vector other) {
        if (this.getSize() != other.getSize()) {
            throw new IllegalArgumentException("Векторы должны быть одинаковой размерности для скалярного произведения.");
        }
        double result = 0;
        for (int i = 0; i < data.length; i++) {
            result += this.at(i) * other.at(i);
        }
        return result;
    }

    /***
     Сравнение двух векторов
     ***/
    public boolean equals(Vector other) {
        if (other == null || this.getSize() != other.getSize()) return false;

        for (int i = 0; i < data.length; i++) {
            if (Math.abs(data[i] - other.getData()[i]) >= EPSILON) {
                return false;
            }
        }
        return true;
    }
}
