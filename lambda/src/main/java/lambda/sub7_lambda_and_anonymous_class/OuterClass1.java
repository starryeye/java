package lambda.sub7_lambda_and_anonymous_class;

public class OuterClass1 {

    // 내부에서 람다를 사용하는 외부 클래스이다.

    public void execute() {

        // 람다
        Runnable anonymous = () -> {
            System.out.println("[lambda internal] this = " + this);
            System.out.println("[lambda internal] this.getClass() = " + this.getClass());
        };

        System.out.println("[lambda] instance = " + anonymous);
        System.out.println("[lambda] instance.getClass() = " + anonymous.getClass());

        anonymous.run();
    }
}
