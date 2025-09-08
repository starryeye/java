package dev.practice.basic.sub7_practice.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class Client {

    private final String host;
    private final int port;

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private ReadHandler readHandler;
    private WriteHandler writeHandler;

    private boolean isClosed = false;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        threadLog("Client::start start");

        socket = new Socket(host, port);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());

        readHandler = new ReadHandler(input, this);
        writeHandler = new WriteHandler(output, this);
        Thread readThread = new Thread(readHandler, "readHandler");
        Thread writeThread = new Thread(writeHandler, "writeHandler");
        readThread.start();
        writeThread.start();

        threadLog("Client::start end");
    }

    public synchronized void close() {
        if (isClosed) {
            return;
        }

        writeHandler.close();
        readHandler.close();
        closeAll();
        isClosed = true;

        threadLog("connection closed: " + socket);
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
}
