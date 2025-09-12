package dev.practice.basic.sub7_practice.chat.server.command;

import dev.practice.basic.sub7_practice.chat.server.command.handler.*;
import dev.practice.basic.sub7_practice.chat.server.session.Session;
import dev.practice.basic.sub7_practice.chat.server.session.SessionManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandHandlerMapping {
    /**
     * CommandHandlerMapping 은 대표적인 Command Pattern 이다.
     */

    private static final String DELIMITER = "\\|";
    private final Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

    private final CommandHandler defaultCommandHandler = new DefaultCommandHandler();

    public CommandHandlerMapping(SessionManager sessionManager) {
        // todo, enum 으로 자동화되도록 변경해보기
        commandHandlerMap.put("/join", new JoinCommandHandler(sessionManager));
        commandHandlerMap.put("/message", new MessageCommandHandler(sessionManager));
        commandHandlerMap.put("/change", new ChangeCommandHandler(sessionManager));
        commandHandlerMap.put("/users", new UsersCommandHandler(sessionManager));
        commandHandlerMap.put("/exit", new ExitCommandHandler());
    }

    public void execute(String request, Session session) throws IOException {

        // todo, execute 에서 Session 파라미터 제거하고 SessionManager 로 control 해보기.. 특정 Session 을 원하면 session 의 username 으로.. session 찾기

        // parsing
        String[] args = request.split(DELIMITER);

        // mapping, Null Object Pattern..
        CommandHandler commandHandler = commandHandlerMap.getOrDefault(args[0], defaultCommandHandler);

        // execute
        commandHandler.execute(args, session);
    }
}
