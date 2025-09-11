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
        if (request.startsWith("/exit")) {
            throw new IOException("exit");
        }

        sessionManager.sendAll(request);
    }
}
