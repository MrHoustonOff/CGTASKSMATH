package Math;

public class Vector4f{
    final Vector vector;

    /***
    Конструктор вектора, принимает одномерный массив
     ***/
    public Vector4f(double[] v) {
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
    Возвращает третью координату вектора
     ***/
    public double getZ() {
        return vector.getData()[2];
    }

    /***
    Возвращает четвертую координату вектора
     ***/
    public double getW() {
        return vector.getData()[3];
    }

    /***
    Возвращает размерность вектора
     ***/
    public int getSize(){
        return 4;
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
    public Vector4f add(Vector4f other) {
        return new Vector4f(vector.add(other.vector).getData());
    }

    /***
    Вычитание векторов
     ***/
    public Vector4f sub(Vector4f other) {
        return new Vector4f(vector.sub(other.vector).getData());
    }

    /***
    Умножение вектора на число
     ***/
    public Vector4f mul(double scalar) {
        return new Vector4f(vector.mul(scalar).getData());
    }

    /***
    Деление вектора на число
     ***/
    public Vector4f div(double scalar) {
        return new Vector4f(vector.div(scalar).getData());
    }

    /***
    Нормализация вектора
     ***/
    public Vector4f normalize() {
        return new Vector4f(vector.normalize().getData());
    }

    /***
    Скалярное произведение векторов
     ***/
    public double dotProduct(Vector4f other) {
        return vector.dotProduct(other.vector);
    }

    /***
     Сравнение двух векторов
     ***/
    public boolean equals(Vector4f other) {
        return vector.equals(other.vector);
    }
}
