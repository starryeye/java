package dev.practice.basic.sub7_practice.chat.client;

import java.io.DataInputStream;
import java.io.IOException;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class ReadHandler implements Runnable {

    private final DataInputStream input;
    private final Client client;
    private boolean isClosed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {

        try {
            while (true) {
                String received = input.readUTF();
                System.out.println(received);
            }
        } catch (IOException e) {
            threadLog(e);
        } finally {
            client.close();
        }
    }

    public synchronized void close() {
        if (isClosed) {
            return;
        }

        isClosed = true;

        threadLog("readHandelr shutdown");
    }
}
