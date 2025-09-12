package dev.practice.basic.sub7_practice.chat.server.command.handler;

import dev.practice.basic.sub7_practice.chat.server.session.Session;

import java.io.IOException;

public interface CommandHandler {

    void execute(String[] args, Session session) throws IOException;
}
