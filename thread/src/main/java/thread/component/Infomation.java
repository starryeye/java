package thread.component;

import static util.MyThreadLog.threadLog;

public class Infomation {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();

        threadLog("toString() = " + mainThread);                            // 이름, 우선순위, 그룹이 출력된다.
        threadLog("getId() = " + mainThread.getId());                       // 스레드 id, 고유 식별자로 JVM 내에서 유일함
        threadLog("getName() = " + mainThread.getName());                   // 스레드 이름, 중복가능
        threadLog("getPriority() = " + mainThread.getPriority());           // 스레드 우선순위, OS 스케쥴러 우선순위 Hint
        threadLog("getThreadGroup() = " + mainThread.getThreadGroup());     // 스레드 그룹, 스레드들을 그룹화하여 관리할 때 사용되는 단위 (기본적으로 자식 스레드는 부모 스레드 그룹에 속함)
        threadLog("getState() = " + mainThread.getState());                 // 스레드 상태, Thread.State enum 값

        /**
         * Thread.State
         *      NEW             // [    생성] start() 호출 전
         *      RUNNABLE        // [실행 가능] CPU 에 의해 실행되고 있거나, OS 스케줄러 실행 대기열에 있거나
         *      BLOCKED         // [    차단] 동기화 락을 기다리는 상태                                     ex. synchronized 블록 진입 대기
         *      WAITING         // [    대기] 무기한으로 다른 스레드의 특정 작업이 완료되기를 기다리는 상태          ex. ..
         *      TIMED_WAITING   // [시간 대기] 일정 시간동안 기다리는 상태                                    ex. sleep()
         *      TERMINATED      // [    종료] 실행을 마친 상태
         *
         *     생성  --> 실행 가능
         * 실행 가능 <--> 일시 중지(차단, 대기, 시간 대기)
         * 실행 가능  --> 종료
         */
    }
}
