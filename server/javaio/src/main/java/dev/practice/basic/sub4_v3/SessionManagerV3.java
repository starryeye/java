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

        for (SessionV3 session : sessions) {
            session.close();
        }

        sessions.clear();
    }
}
