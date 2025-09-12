package dev.practice.basic.sub7_practice.chat.server.command.handler;

import dev.practice.basic.sub7_practice.chat.server.session.Session;
import dev.practice.basic.sub7_practice.chat.server.session.SessionManager;

import java.io.IOException;
import java.util.List;

public class UsersCommandHandler implements CommandHandler {

    private final SessionManager sessionManager;

    public UsersCommandHandler(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {

        List<String> usernames = sessionManager.getAllUsername();

        StringBuilder sb = new StringBuilder();
        sb.append("List of all connected users : ").append(usernames.size()).append(" people").append("\n");
        for (String username : usernames) {
            sb.append(" - ").append(username).append("\n");
        }

        session.send(sb.toString());
    }
}
