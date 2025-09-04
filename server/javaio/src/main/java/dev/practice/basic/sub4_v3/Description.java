package dev.practice.basic.sub4_v3;

public interface Description {

    /**
     * V2 와 비교하여 V3 는..
     *      server 에 Java 의 Shutdown hook 을 적용하여, server 가 종료될 때, 현재 정리되지 않은 모든 자원을 close 하도록한다.
     *      정리 대상
     *          ServerSocket, Socket, InputStream, OutputStream
     *
     * Shutdown hook (셧다운 훅)
     *      Java 프로세스가 "정상 종료"될 때, 자원 정리나 로그를 남길 수 있도록 지원하는 callback 기능
     *      셧다운 훅 등록
     *          Runtime.getRuntime().addShutdownHook(new Thread(Runnable 구현 객체, "shutdown"));
     *
     * Java 프로세스 종료
     *      정상 종료
     *          모든 유저스레드의 실행 완료될 때
     *          개발자가 Ctrl + C 로 프로세스 중단
     *          개발자가 command line 으로 kill 명령 (kill -9 는 강제종료임)
     *          개발자가 Intellij 의 stop 버튼 누를 때
     *      강제 종료
     *          OS 에서 프로세스를 더이상 유지 할 수 없다고 판단할 때
     *          linux 의 kill -9 나 windows 의 taskkill /F
     */
}
