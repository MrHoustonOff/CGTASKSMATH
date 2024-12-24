package Math;

public class Vector3f{
    final Vector vector;

    /***
    Конструктор вектора, принимает одномерный массив
     ***/
    public Vector3f(double[] v) {
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
    Возвращает размерность вектора
     ***/
    public int getSize(){
        return 3;
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
    public Vector3f add(Vector3f other) {
        return new Vector3f(vector.add(other.vector).getData());
    }

    /***
    Вычитание векторов
     ***/
    public Vector3f sub(Vector3f other) {
        return new Vector3f(vector.sub(other.vector).getData());
    }

    /***
    Умножение вектора на число
     ***/
    public Vector3f mul(double scalar) {
        return new Vector3f(vector.mul(scalar).getData());
    }

    /***
    Деление вектора на число
     ***/
    public Vector3f div(double scalar) {
        return new Vector3f(vector.div(scalar).getData());
    }

    /***
    Нормализация вектора
     ***/
    public Vector3f normalize() {
        return new Vector3f(vector.normalize().getData());
    }

    /***
    Скалярное произведение векторов
     ***/
    public double dotProduct(Vector3f other) {
        return vector.dotProduct(other.vector);
    }

    /***
     Векторное произведение
     ***/
    public Vector3f crossProduct(Vector3f other) {
        double[] v = new double[]{this.getY() * other.getZ() - this.getZ() * other.getY(),
                this.getZ() * other.getX() - this.getX() * other.getZ(),
                this.getX() * other.getY() - this.getY() * other.getX()};
        return new Vector3f(v);
    }

    /***
     Сравнение двух векторов
     ***/
    public boolean equals(Vector3f other) {
        return vector.equals(other.vector);
    }

}
