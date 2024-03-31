package dev.practice.java17.sealed.example;

public non-sealed class Rectangle extends Parallelogram{

    /**
     * non-sealed
     * 직사각형을 상속하는 타입은 특별한 제한을 두지 않는다.
     */


    public Rectangle(double base, double height) {
        super(base, height);
    }

    // Rectangle 클래스는 추가적인 속성 없이 Parallelogram의 area 계산법을 상속받는다.
}
