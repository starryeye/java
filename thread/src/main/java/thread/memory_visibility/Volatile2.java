package thread.memory_visibility;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class Volatile2 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        t.start();

        mySleep(1000);

        task.flag = false;
        threadLog("flag = " + task.flag + ", count = " + task.count + " in main");
    }

    private static class MyTask implements Runnable {

        //boolean flag = true; // volatile 키워드를 사용하지 않으면, 즉시 종료되지 않는다. (성능은 빠름) // log 를 찍을때 context switching 을 하면서 메인메모리와 CPU 코어 캐시메모리 사이의 싱크가 맞춰지면서 늦게라도 종료됨.
        //long count;
        volatile boolean flag = true; // volatile 키워드를 사용하면, 즉시 종료된다. (성능은 좀 느려짐)
        volatile long count;



        @Override
        public void run() {

            while(flag) {

                count++;
                if (count % 100_000_000 == 0) { // 1억번에 한번씩 출력
                    threadLog("task run.. flag = " + flag + ", count = " + count);
                }
            }
            threadLog("task end.. flag = " + flag + ", count = " + count);
        }
    }
}
