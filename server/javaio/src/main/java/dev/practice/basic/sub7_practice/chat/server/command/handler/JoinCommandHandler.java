package dev.practice.basic.sub7_practice.chat.server.command.handler;

import dev.practice.basic.sub7_practice.chat.server.session.Session;
import dev.practice.basic.sub7_practice.chat.server.session.SessionManager;

import java.io.IOException;

public class JoinCommandHandler implements CommandHandler {

    private final SessionManager sessionManager;

    public JoinCommandHandler(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {

        String username = args[1];

        session.setUsername(username);

        sessionManager.sendAll(username + " joined!");
    }
}
