package collection.deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class JavaDequeUsingStackTest {

    public static void main(String[] args) {

        /**
         * Deque 를 Stack 으로 사용
         */

        Deque<Integer> deque = new ArrayDeque<>();
        //Deque<Integer> deque = new LinkedList<>();

        // push
        deque.push(1);
        deque.push(2);
        deque.push(3);
        System.out.println(deque);

        // 다음 꺼낼 데이터 확인(꺼내지는 않음)
        System.out.println("deque.peek() = " + deque.peek());

        // pop
        System.out.println("pop = " + deque.pop());
        System.out.println("pop = " + deque.pop());
        System.out.println("pop = " + deque.pop());
        System.out.println(deque);
    }
}
