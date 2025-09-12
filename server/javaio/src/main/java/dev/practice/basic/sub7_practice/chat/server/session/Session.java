package dev.practice.basic.sub7_practice.chat.server.session;

import dev.practice.basic.sub7_practice.chat.server.command.CommandHandlerMapping;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class Session implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final CommandHandlerMapping commandHandlerMapping;
    private final SessionManager sessionManager;

    private boolean isClosed = false;

    private String username;

    public Session(Socket socket, CommandHandlerMapping commandHandlerMapping, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.commandHandlerMapping = commandHandlerMapping;
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while (true) {

                String request = input.readUTF();
                threadLog("client -> server: " + request);

                commandHandlerMapping.execute(request, this);
            }
        } catch (IOException e) {
            threadLog(e);
        } finally {
            sessionManager.remove(this);
            sessionManager.sendAll(username + " has left..");
            close();
        }
    }

    public synchronized void close() {

        if (isClosed) {
            return;
        }

        isClosed = true;
        closeAll();

        threadLog("connection closed : " + socket + ", Socket::isClosed() : " + socket.isClosed());
    }

    public void send(String message) throws IOException {
        threadLog("server -> client: " + message);
        output.writeUTF(message);
    }

    private void closeAll() {
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                threadLog(e.getMessage());
            }
        }
        if (output != null) {
            try {
                output.close();
            } catch (IOException e) {
                threadLog(e.getMessage());
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                threadLog(e.getMessage());
            }
        }
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String newName) {
        this.username = newName;
    }
}
