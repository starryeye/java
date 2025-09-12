package dev.practice.basic.sub7_practice.chat.server.command.handler;

import dev.practice.basic.sub7_practice.chat.server.session.Session;

import java.io.IOException;

public class ExitCommandHandler implements CommandHandler {
    @Override
    public void execute(String[] args, Session session) throws IOException {
        throw new IOException("The client sends an exit command..");
    }
}
