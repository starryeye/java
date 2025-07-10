public class AutoboxingUnboxing2 {

    public static void main(String[] args) {

        int a = 199;
        Integer b = 199;

        /**
         * b 는 primitive type, c 는 reference type 이라서
         * c 를 auto unboxing 후 동일 비교 연산자를 수행한다. (값 비교가 됨)
         */
        System.out.println("a == b -> " + (a == b));     // System.out.println(a == b.intValue());


        /**
         * b 는 reference type, c 도 reference type 이라서
         * 참조 주소 비교가 된다. (동일성)
         */
        Integer c = 199;
        System.out.println("b == c -> " + (b == c));     // System.out.println(b == c);


        /**
         * c 와 d 는 동일한 인스턴스를 가리키고 있었으나..
         * d *= 1 을 수행하면서..
         *      d 가 auto unboxing, auto boxing 작업을 수행하고 불변객체라서
         *      다른 참조값을 가리키게 된다.(값은 동등)
         *
         * -> reference type class 간의 값 비교를 수행하고 싶다면 eqauls() 메서드를 사용해야한다.
         */
        Integer d = c;                                      // c 와 d 는 동일한 인스턴스를 가리킴
        System.out.println("c == d -> " + (c == d));
        d *= 1;                                             // Integer.valueOf(d.intValue() * 1);
        System.out.println("c == d -> " + (c == d));        // System.out.println(c == d);
        System.out.println("c.equals(d) -> " + (c.equals(d)));


        /**
         * 코드상으로 보면 서로다른 인스턴스이고 reference type 간 동일성 비교이므로 false 가 나와야 할 것 같지만..
         * Integer caching 때문에 -128 ~ 127 범위의 정수 값은 동일한 인스턴스를 가리키게 되어 true 가 나온다.
         *
         * 마치 String pool 과 비슷하게 되는데.. 그래도 값 비교시에는 항상 eqauls() 메서드를 사용하도록 하자.
         */
        Integer e = 127;
        Integer f = 127;
        System.out.println("e == f -> " + (e == f));
    }
}
