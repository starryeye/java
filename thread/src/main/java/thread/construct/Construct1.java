package thread.construct;

import util.MyThreadLog;

import static util.MyThreadLog.threadLog;

public class Construct1 {

    /**
     * Thread class 를 상속한 class 로 Thread 를 만들고 실행할 수 있다.
     *
     * Thread::start()
     *      1. start() 메서드가 호출된 스레드 인스턴스(MyThread instance)에 대하여,
     *          해당 스레드(MyThread instance)에 stack 메모리 공간을 할당한다.
     *      2. 해당 스레드(MyThread instance)가 자기 자신의 run() 메서드를 호출한다.
     *
     * 참고
     * Java 는 실행 시점에 main 이라는 이름의 스레드를 생성하고 stack 메모리 공간을 할당해주고
     * main 스레드는 main() 메서드를 호출한다.
     *
     * 참고.
     * main 스레드가 myThread.start() 를 호출 하지 않고, myThread.run() 를 직접 호출하면 새로운 MyThread 가 수행하지 않는다.
     *      단순히 MyThread 객체 덩어리의 run() 메서드를 main 스레드가 실행하는 것이다.
     */

    public static void main(String[] args) {
        threadLog("is start");

        MyThread myThread = new MyThread(); // main 스레드가 MyThread instance 생성 (여기 까지는 객체 덩어리에 불과함)
        myThread.start(); // main 스레드가 MyThread start() 메서드 호출 (MyThread 에게 stack 메모리 영역 할당, MyThread 는 run() 메서드를 실행)

        threadLog("is end");
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            threadLog("is running");
        }
    }
}
