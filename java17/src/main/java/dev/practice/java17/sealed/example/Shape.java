package dev.practice.java17.sealed.example;

public sealed interface Shape permits Circle, Parallelogram, Triangle {

    /**
     * sealed 키워드와 permits 키워드를 사용하여 Shape sealed 타입은 ..
     * Circle, Parallelogram, Triangle 하위 타입으로만 구현, 상속 가능하다.
     */

    double area();
}
