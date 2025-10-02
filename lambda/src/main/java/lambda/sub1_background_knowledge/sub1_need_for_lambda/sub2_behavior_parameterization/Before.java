package lambda.sub1_background_knowledge.sub1_need_for_lambda.sub2_behavior_parameterization;

import java.util.Random;

public class Before {

    /**
     * rollDice(), diceValues() 메서드에 존재하는
     * 공통의 중복 코드를 제거해야한다...
     * -> 해결 : After.java
     */

    public static void main(String[] args) {
        rollDice();
        diceValues();
    }

    private static void rollDice() {
        long startNs = System.nanoTime();


        // 변하는 부분 시작
        int randomValue = new Random().nextInt(6) + 1;
        System.out.println("dice result = " + randomValue);
        // 변하는 부분 끝


        long endNs = System.nanoTime();
        System.out.println("elapsed = " + (endNs - startNs) + "ns");
    }

    private static void diceValues() {
        long startNs = System.nanoTime();


        // 변하는 부분 시작
        for (int i = 1; i <= 6; i++) {
            System.out.println("i = " + i);
        }
        // 변하는 부분 끝


        long endNs = System.nanoTime();
        System.out.println("elapsed = " + (endNs - startNs) + "ns");
    }
}
