package dev.practice.basic.sub7_practice.chat.server.command.handler;

import dev.practice.basic.sub7_practice.chat.server.session.Session;
import dev.practice.basic.sub7_practice.chat.server.session.SessionManager;

import java.io.IOException;

public class MessageCommandHandler implements CommandHandler {

    private final SessionManager sessionManager;

    public MessageCommandHandler(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {

        String message = args[1];

        sessionManager.sendAll("[" + session.getUsername() + "] " + message);
    }
}
