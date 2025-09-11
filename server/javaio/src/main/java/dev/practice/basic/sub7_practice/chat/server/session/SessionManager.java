package dev.practice.basic.sub7_practice.chat.server.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class SessionManager {

    /**
     * server 가 현재 사용 중인 Socket(InputStream, OutputStream 포함) 들을 관리하는 객체이다.
     *
     */

    private List<Session> sessions = new ArrayList<>();

    // 동기화 락
    public synchronized void add(Session session) {
        sessions.add(session);
    }

    // 동기화 락
    public synchronized void remove(Session session) {
        sessions.remove(session);
    }

    // 동기화 락
    public synchronized void clear() {

        for (Session session : sessions) {
            session.close();
        }

        sessions.clear();
    }

    // 동기화 락
    public synchronized void sendAll(String message) {
        for (Session session : sessions) {
            try {
                session.send(message);
            } catch (IOException e) {
                threadLog(e);
            }
        }
    }

    // 동기화 락
    public synchronized List<String> getAllUsername() {
        List<String> usernames = new ArrayList<>();
        for (Session session : sessions) {
            if (session.getUsername() != null) {
                usernames.add(session.getUsername());
            }
        }
        return usernames;
    }
}
