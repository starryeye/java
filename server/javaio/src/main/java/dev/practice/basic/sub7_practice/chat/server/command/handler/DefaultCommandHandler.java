package dev.practice.basic.sub7_practice.chat.server.command.handler;

import dev.practice.basic.sub7_practice.chat.server.session.Session;

import java.io.IOException;

public class DefaultCommandHandler implements CommandHandler {

    @Override
    public void execute(String[] args, Session session) throws IOException {
        session.send("The (" + args[0] + ") command is an invalid command.");
    }
}
