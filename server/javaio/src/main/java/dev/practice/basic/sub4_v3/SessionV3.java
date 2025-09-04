package dev.practice.basic.sub4_v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class SessionV3 implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV3 sessionManager;
    private boolean closed = false;

    public SessionV3(Socket socket, SessionManagerV3 sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {

        try {
            while (true) {
                // client 가 보낸 메시지 읽기
                String received = input.readUTF();
                threadLog("client -> server: " + received);

                if (received.equals("exit")) {
                    break;
                }

                // client 로 메시지 보내기
                String toSend = received + " World!";
                output.writeUTF(toSend);
                threadLog("client <- server: " + toSend);
            }
        } catch (IOException e) {
            threadLog(e);
        } finally {
            sessionManager.remove(this);
            close();
        }
    }

    // 동기화 락
    public synchronized void close() {
        if (closed) {
            return;
        }
        closeAll(); // SessionV3 모든 자원 정리
        closed = true;
        threadLog("connection closed : " + socket + ", Socket::isClosed() : " + socket.isClosed());
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
