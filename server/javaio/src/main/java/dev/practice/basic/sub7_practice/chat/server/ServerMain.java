package dev.practice.basic.sub7_practice.chat.server;

import dev.practice.basic.sub7_practice.chat.server.command.CommandHandlerMapping;
import dev.practice.basic.sub7_practice.chat.server.session.SessionManager;

import java.io.IOException;

public class ServerMain {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

        SessionManager sessionManager = new SessionManager();
        CommandHandlerMapping commandHandlerMapping = new CommandHandlerMapping(sessionManager);
        Server server = new Server(PORT, sessionManager, commandHandlerMapping);

        server.start();
    }
}
