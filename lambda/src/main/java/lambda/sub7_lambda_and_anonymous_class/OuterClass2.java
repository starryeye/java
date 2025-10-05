package lambda.sub7_lambda_and_anonymous_class;

public class OuterClass2 {

    // 내부에서 익명 클래스를 사용하는 외부 클래스이다.

    public void execute() {

        // 익명 클래스
        Runnable anonymous = new Runnable() {

            private String message = "anonymous class field"; // 익명 클래스는 자체 필드를 가질 수 있음.

            @Override
            public void run() {

                System.out.println("[anonymous class internal] this = " + this);
                System.out.println("[anonymous class internal] this.getClass() = " + this.getClass());
            }
        };

        System.out.println("[anonymous class] instance = " + anonymous);
        System.out.println("[anonymous class] instance.getClass() = " + anonymous.getClass());

        anonymous.run();
    }
}
