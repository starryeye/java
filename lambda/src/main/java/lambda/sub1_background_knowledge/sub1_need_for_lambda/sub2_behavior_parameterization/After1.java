package lambda.sub1_background_knowledge.sub1_need_for_lambda.sub2_behavior_parameterization;

import java.util.Random;

public class After1 {

    /**
     * sub1_value_parameterization 과 비교했을 때 변경되는 부분이 단일 값이 아니라 넓은 코드 영역이다..
     *
     * Behavior Parameterization (값 매개변수화)
     *      구체적인 동작(여러 코드 라인)을 메서드 내에 두지 않고 매개변수를 통해
     *      외부에서 전달 받도록 하여, 매개변수에 따라 메서드의 동작을 달리하도록 한다. (재사용성 up)
     */

    public static void main(String[] args) {

        elapsedDice(new RollDice());        // 동작을 결정하는 코드 영역을 인스턴스로 전달
        elapsedDice(new DiceValues());      // 동작을 결정하는 코드 영역을 인스턴스로 전달
    }

    public static void elapsedDice(Procedure procedure) { // 변하는 영역을 behavior parameterization 을 적용하였다.
        long startNs = System.nanoTime();


        procedure.execute(); //Before.java 와 비교하여 변하지 않는 부분으로 변경됨


        long endNs = System.nanoTime();
        System.out.println("elapsed = " + (endNs - startNs) + " ns");
    }

    private static class RollDice implements Procedure {

        @Override
        public void execute() {
            int randomValue = new Random().nextInt(6) + 1;
            System.out.println("dice result = " + randomValue);
        }
    }

    private static class DiceValues implements Procedure {

        @Override
        public void execute() {
            for (int i = 1; i <= 6; i++) {
                System.out.println("i = " + i);
            }
        }
    }
}
