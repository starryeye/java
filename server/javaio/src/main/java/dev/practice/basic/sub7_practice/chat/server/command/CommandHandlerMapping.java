package dev.practice.basic.sub7_practice.chat.server.command;

import dev.practice.basic.sub7_practice.chat.server.session.Session;
import dev.practice.basic.sub7_practice.chat.server.session.SessionManager;

import java.io.IOException;

public class CommandHandlerMapping {

    private final SessionManager sessionManager;

    public CommandHandlerMapping(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void execute(String request, Session session) throws IOException {

        // todo, execute 에서 Session 파라미터 제거하고 SessionManager 로 control 해보기.. 특정 Session 을 원하면 session 의 username 으로.. session 찾기
        if (request.startsWith("/exit")) {
            throw new IOException("exit");
        }

        sessionManager.sendAll(request);
    }
}
