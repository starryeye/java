public class AutoboxingUnboxing1 {

    public static void main(String[] args) {

        /**
         * Auto boxing
         *      Java 컴파일러가 자동으로, primitive type 값을 wrapper class 객체로 변환
         * Auto unboxing
         *      Java 컴파일러가 자동으로, wrapper class 객체를 primitive type 값으로 변환
         */
        Integer a = 199;    // Integer a = Integer.valueOf(199);    // auto boxing
        int b = a;          // int b = a.intValue();                // auto unboxing
        System.out.println("a 인스턴스 참조 값 = " + Integer.toHexString(System.identityHashCode(a)));


        /**
         * a++
         *      1. a 를 auto unboxing 하여 값을 꺼내고 +1 연산을 수행
         *      2. 연산의 결과를 auto boxing 하여 wrapper 객체로 변환
         *      3. wrapper class 는 불변 객체라 기존의 인스턴스가 아니라 새로운 인스턴스가 됨.
         *
         * b++
         *      1. stack 메모리 상의 값을 +1 해줌. 끝.
         */
        a++;                // a = Integer.valueOf(a.intValue() + 1);
        b++;                // b = b + 1
        System.out.println("a 인스턴스 참조 값 = " + Integer.toHexString(System.identityHashCode(a)));


        /**
         * a++ 연산에서 일어났던 작업과 비슷하게
         * auto unboxing 이 일어나고 연산이 수행된 후, auto boxing 을 수행하여 최종 인스턴스 참조값을 c 에 대입한다.
         */
        Integer c = a * a;      // Integer c = Integer.valueOf(a.intValue() * a.intValue());
    }
}
