package lambda.sub1_background_knowledge.sub1_need_for_lambda.sub2_behavior_parameterization;

import java.util.Random;

public class After2 {

    /**
     * After1 과 비교하면 동작을 결정하는 코드영역을..
     *      클래스를 만들지 않고 익명 클래스 인스턴스로 전달한다.
     */

    public static void main(String[] args) {

        elapsedDice(new Procedure() {
            @Override
            public void execute() {
                int randomValue = new Random().nextInt(6) + 1;
                System.out.println("dice result = " + randomValue);
            }
        });        // 동작을 결정하는 코드 영역을 인스턴스로 전달
        elapsedDice(new Procedure() {
            @Override
            public void execute() {
                for (int i = 1; i <= 6; i++) {
                    System.out.println("i = " + i);
                }
            }
        });      // 동작을 결정하는 코드 영역을 인스턴스로 전달
    }

    public static void elapsedDice(Procedure procedure) { // 변하는 영역을 behavior parameterization 을 적용하였다.
        long startNs = System.nanoTime();


        procedure.execute(); //Before.java 와 비교하여 변하지 않는 부분으로 변경됨


        long endNs = System.nanoTime();
        System.out.println("elapsed = " + (endNs - startNs) + " ns");
    }
}
