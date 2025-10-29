package sub6_or_else_and_or_else_get;

import java.util.Optional;

public class OrElseAndOrElseGet {

    /**
     * orElse, orElseGet 의 비교는
     * 즉시평가, 지연평가의 차이를 알게 해준다.
     *
     * 즉시 평가
     *      연산을 선언하는 시점과 해당 연산을 실행하는 시점이 동일
     *          일반적으로 메서드 호출
     * 지연 평가
     *      연산을 선언하는 시점이 해당 연산을 실행하는 시점보다 빠름
     *          람다를 선언해 놓고 해당 람다를 실행하는 코드를 나중에 호출
     *          익명 클래스를 만들고 해당 메서드를 나중에 호출
     *
     * orElse(T other)
     *      orElse 연산보다 파라미터의 연산이 먼저 수행된다. (즉시 평가)
     *          Optional 의 내부 값이 존재하면, 이미 연산된 파라미터 값은 무시되고 내부 값이 반환됨.
     *          Optional 의 내부 값이 존재하지 않으면, 이미 연산된 파라미터 값이 반환됨
     * orElseGet(Supplier<? extends T> supplier)
     *      파라미터로 전달된 Supplier 는 orElseGet 보다 늦게 수행된다. (지연 평가)
     *          Optional 의 내부 값이 존재하면, Supplier 는 수행되지 않고 내부 값이 반환됨.
     *          Optional 의 내부 값이 존재하지 않으면, 파라미터로 전달된 Supplier 가 수행되고 그 값이 반환됨.
     *
     * 결론.
     * orElse 는 other 를 항상 미리 계산
     * orElseGet 은 내부 값이 있을 때 supplier 를 실행하지 않는다.
     * 따라서..
     *      대체 값의 생성 비용이 크지 않은 단순 값이거나, 내부 값이 거의 대부분 존재하지 않을 때
     *          -> orElse
     *      대체 값의 생성 비용이 크거나, 내부 값이 대부분 존재할 때
     *          -> orElseGet
     */

    public static void main(String[] args) {

        Optional<Integer> optValue = Optional.of(100);
        Optional<Integer> optEmpty = Optional.empty();


        // orElse
        System.out.println("[optValue.orElse]");
        Integer result1 = optValue.orElse(createData());
        System.out.println("[optValue.orElse] result = " + result1);

        System.out.println("[optEmpty.orElse]");
        Integer result2 = optEmpty.orElse(createData());
        System.out.println("[optEmpty.orElse] result = " + result2);



        // orElseGet
        System.out.println("[optValue.orElseGet]");
        Integer result3 = optValue.orElseGet(() -> createData());
        System.out.println("[optValue.orElseGet] result = " + result3);

        System.out.println("[optEmpty.orElseGet]");
        Integer result4 = optEmpty.orElseGet(() -> createData());
        System.out.println("[optEmpty.orElseGet] result = " + result4);
    }

    private static int createData() {

        System.out.println("createData method called..");

        // 오래 걸리는 작업이라 가정한다.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return 50;
    }
}
