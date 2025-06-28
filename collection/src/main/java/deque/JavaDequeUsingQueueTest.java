package deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class JavaDequeUsingQueueTest {

    public static void main(String[] args) {

        /**
         * Deque 를 Queue 로 사용
         */
        Deque<Integer> deque = new ArrayDeque<>();
        //Deque<Integer> deque = new LinkedList<>();

        // offer
        deque.offer(1);
        deque.offer(2);
        deque.offer(3);
        System.out.println(deque);

        //다음 꺼낼 데이터 확인(꺼내지는 않음)
        System.out.println("deque.peek() = " + deque.peek());

        // poll
        System.out.println("poll = " + deque.poll());
        System.out.println("poll = " + deque.poll());
        System.out.println("poll = " + deque.poll());
        System.out.println(deque);
    }
}
