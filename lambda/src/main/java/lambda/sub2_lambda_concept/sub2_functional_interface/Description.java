package lambda.sub2_lambda_concept.sub2_functional_interface;

public interface Description {

    /**
     * Functional Interface 에 대해 알아본다.
     *
     * 함수형 인터페이스(Functional Interface)
     *      함수형 인터페이스는 정확히 하나의 추상 메서드(SAM, single abstract method)를 가지는 인터페이스이다.
     *      람다는 함수형 인터페이스에만 할당할 수 있다.
     *          클래스나 추상클래스에는 할당 불가능하다.
     *      람다는 하나의 코드 영역에 대한 익명 함수이므로 "정확히 하나" 에 대한 개념이 함수형 인터페이스와 일치한다.
     *      @FunctionalInterface annotation 을 인터페이스에 적용하면..
     *          컴파일러가 해당 인터페이스가 정확히 하나의 추상 메서드를 가지는지 검사해준다.
     *
     * 람다를 함수형 인터페이스에 할당할때는 메서드 시그니처와 반환 타입이 일치해야한다.
     *      메서드 시그니처가 동일하다는 의미는 메서드 이름과 매개변수 갯수와 매개변수의 선언 순서에 따른 타입들이 일치하는지를 뜻하는데
     *      람다는 익명 함수이므로 메서드 이름과는 상관 없다. (어차피 람다에는 메서드 이름이 없음)
     */
}
