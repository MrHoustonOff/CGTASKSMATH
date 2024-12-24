package Math;

public class Vector2f{
    final Vector vector;

    /***
    Конструктор вектора, принимает одномерный массив
     ***/
    public Vector2f(double[] v) {
        this.vector = new Vector(v);
    }

    /***
    Возвращает копию вектора
     ***/
    public double[] getData() {
        return vector.getData();
    }

    /***
    Возвращает первую координату вектора
     ***/
    public double getX() {
        return vector.getData()[0];
    }

    /***
    Возвращает вторую координату вектора
     ***/
    public double getY() {
        return vector.getData()[1];
    }

    /***
    Возвращает размерность вектора
     ***/
    public int getSize(){
        return 2;
    }

    /***
    Возвращает значение элемента вектора под индексом idx
     ***/
    public double at(int idx) {
        return vector.getData()[idx];
    }

    /***
    Изменяет значение элемента вектор под индексом idx на значение value
     ***/
    public void setAt(int idx, double value) {
        vector.setAt(idx, value);
    }

    /***
    Возвращает длину вектора
     ***/
    public double length() {
        return vector.length();
    }

    /***
    Вывод вектора в консоль
     ***/
    public void print() {
        vector.print();
    }

    /***
    Сложение векторов
     ***/
    public Vector2f add(Vector2f other) {
        return new Vector2f(vector.add(other.vector).getData());
    }

    /***
    Вычитание векторов
     ***/
    public Vector2f sub(Vector2f other) {
        return new Vector2f(vector.sub(other.vector).getData());
    }

    /***
    Умножение вектора на число
     ***/
    public Vector2f mul(double scalar) {
        return new Vector2f(vector.mul(scalar).getData());
    }

    /***
    Деление вектора на число
     ***/
    public Vector2f div(double scalar) {
        return new Vector2f(vector.div(scalar).getData());
    }

    /***
    Нормализация вектора
     ***/
    public Vector2f normalize() {
        return new Vector2f(vector.normalize().getData());
    }

    /***
    Скалярное произведение векторов
     ***/
    public double dotProduct(Vector2f other) {
        return vector.dotProduct(other.vector);
    }

    /***
     Сравнение двух векторов
     ***/
    public boolean equals(Vector2f other) {
        return vector.equals(other.vector);
    }
}
