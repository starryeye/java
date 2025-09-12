package dev.practice.basic.sub4_v3;

import java.util.ArrayList;
import java.util.List;

public class SessionManagerV3 {

    /**
     * server 가 현재 사용 중인 Socket(InputStream, OutputStream 포함) 들을 관리하는 객체이다.
     *
     */

    private List<SessionV3> sessions = new ArrayList<>();

    // 동기화 락
    public synchronized void add(SessionV3 session) {
        sessions.add(session);
    }

    // 동기화 락
    public synchronized void remove(SessionV3 session) {
        sessions.remove(session);
    }

    // 동기화 락
    public synchronized void clear() {

        /**
         * 모든 세션을 종료한다.
         * 세션이 사용하고 있는 Socket, InputStream, OutputStream 자원들을 정리하는 close 메서드를 호출한다.
         */
        for (SessionV3 session : sessions) {
            session.close();
        }

        sessions.clear();
    }
}
