package lambda.sub7_lambda_and_anonymous_class;

public class CompareTest {

    /**
     * 익명 클래스와 람다를 비교해보기 위해..
     * OuterClass1 은 내부에 람다를 사용하고 있고
     * OuterClass2 는 내부에 익명 클래스를 사용하도록 하고 테스트한다.
     *
     * 익명 클래스
     *      이름이 없다.
     *      Class<T> 타입의 클래스 메타데이터 인스턴스와 구현한 인터페이스 타입에 인스턴스가 할당된다.
     *      하나의 클래스로 여러 메서드를 구현 및 오버라이딩 할 수 있다.
     *      하나의 클래스이므로 자체 필드를 가질 수 있다.
     *      .class 파일이 생성된다.
     *      익명 클래스 내부에서 this 는 익명 클래스 자기 자신의 인스턴스를 가리킨다.
     *      익명 클래스 외부의 변수에 접근할 수 있지만, final 혹은 effectively final 변수여야 한다. (변수 캡처링 가능)
     *
     *
     * 람다(익명 함수)
     *      이름이 없다.
     *      Class<T> 타입의 클래스 메타데이터 인스턴스와 구현한 인터페이스 타입에 인스턴스가 할당된다.
     *      하나의 함수로 하나의 메서드만 구현할 수 있다. (함수형 인터페이스만 구현가능)
     *      인스턴스가 만들어져서 클래스처럼 느껴질 수 있으나 개념상 하나의 메서드로 자체 필드를 가질 수 없다. (메서드 이므로 지역변수와 매개변수는 받는다.)
     *      .class 파일이 생성되지 않고 JVM 메모리내에서 대체 역할이 수행된다. (invokeDynamic 매커니즘)
     *      람다 내부에서 this 는 람다를 사용중인 외부 클래스 인스턴스를 가리킨다.
     *      익명 클래스 외부의 변수에 접근할 수 있지만, final 혹은 effectively final 변수여야 한다. (변수 캡처링 가능)
     *
     * 참고
     *      익명 클래스는 람다와 다르게 필드를 가질 수 있고 여러 메서드를 가질 수 있다.
     *      만약 익명 클래스와 람다 둘다 사용할 수 있는 상황이면 람다를 선택하고..
     *      그게 안되면 익명 클래스를 선택하자.
     */

    public static void main(String[] args) {


        OuterClass1 outerObjectOfLambda = new OuterClass1();
        OuterClass2 outerObjectOfAnonymousClass = new OuterClass2();


        System.out.println("[OuterClass1] this = " + outerObjectOfLambda);
        System.out.println("[OuterClass1] this.getClass() = " + outerObjectOfLambda.getClass());
        outerObjectOfLambda.execute();

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println();

        System.out.println("[OuterClass2] this = " + outerObjectOfAnonymousClass);
        System.out.println("[OuterClass2] this.getClass() = " + outerObjectOfAnonymousClass.getClass());
        outerObjectOfAnonymousClass.execute();
    }
}
