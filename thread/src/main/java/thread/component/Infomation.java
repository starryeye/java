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
    }
}
