package dev.practice.java17.sealed;

import dev.practice.java17.sealed.example.*;

public class Main {

    /**
     * 참고
     * type : class, interface, enum 등을 의미함
     *
     * Java 17 에 추가된 sealed 키워드를 알아본다.
     *
     * 용어 정리 (sealed 키워드 vs sealed 타입)
     * - sealed 키워드
     *  - Java 17에서는 sealed 키워드를 도입하여 클래스의 상속을 제한할 수 있다. 라는 의미로 사용된다.
     * - sealed 타입
     *  - 특정 클래스나 인터페이스가 sealed 키워드를 사용하여 선언되었을 때, 그 클래스나 인터페이스를 가리키는 용어로 사용된다.
     *
     * sealed
     * - 상속할 수 있는 타입을 제한하는 기능이다.
     *
     * 특징
     * - sealed 키워드는 interface, class 키워드 앞에 위치한다.
     * - sealed 키워드는 permits 키워드와 같이 사용된다.
     * - permits 키워드
     *  - extends, implements 뒤나 클래스 명 뒤에 위치한다.
     *  - permits 로 허용하는 하위 타입을 지정할 수 있다.
     *
     * sealed 타입을 상속한 타입(인터페이스, 클래스)의 제약
     * - 다음 중 하나의 키워드를 사용해줘야한다.
     *  - final : 상속을 막는다는 의미
     *  - sealed(+ permits) : 상속을 특정 타입의 하위로만 제한하는 의미
     *  - non-sealed : 상속을 특정 타입으로 제한하지 않겠다는 의미
     * - sealed 타입과 같은 패키지에 위치해야한다.
     *
     *
     * 참고
     * - sealed 타입을 상속 받은 타입이 sealed 타입과 같은 java 파일에 존재하면 permits 생략가능
     * - record 타입도 sealed 인터페이스 타입을 상속할 수 있다..
     */

    public static void main(String[] args) {

        // Shape 은 Circle, Triangle, Parallelogram 하위 타입으로만 상속 또는 구현할 수 있다. (sealed, permits : 제한적인 확장)
        // Circle, Triangle 은 더이상 상속할 수 없다 (final : 확장 금지)
        // Parallelogram 은 Rectangle 하위 타입으로만 상속할 수 있다. (sealed, permits : 제한적인 확장)
        // Rectangle 은 상속에 제한을 두지 않는다. (non-sealed, 확장 가능)

        Shape circle = new Circle(5);
        Shape triangle = new Triangle(3, 4);
        Shape parallelogram = new Parallelogram(4, 5);
        Shape rectangle = new Rectangle(4, 5);

        System.out.println("Circle area: " + circle.area());
        System.out.println("Triangle area: " + triangle.area());
        System.out.println("Parallelogram area: " + parallelogram.area());
        System.out.println("Rectangle area: " + rectangle.area());
    }
}
