package dev.practice.java17.sealed.example;

public sealed class Parallelogram implements Shape permits Rectangle{

    /**
     * sealed
     * 평행사변형을 상속하려면 직사각형의 하위 타입으로 제한한다.
     */

    // Parallelogram 은 평행 사변형

    private final double base;
    private final double height;

    public Parallelogram(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return base * height;
    }
}
