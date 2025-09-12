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
    private boolean isClosed = false;

    public SessionV3(Socket socket, SessionManagerV3 sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {

        /**
         * sub3_v2 에서는 try with resources 를 사용하였지만,
         * 여기서는 외부 SessionManager 에 의해 자원정리가 되어야 하므로 close 메서드를 따로 추출할 필요가 있어서
         * 전통적인 try catch finally 로 구성해야 했다..
         */
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

        /**
         * 서버를 셧다운 할 때, 전체 Session 을 종료하기 위해 SessionManager 에 의해 호출되거나..
         * 단순 client 와의 연결이 종료될 때 호출될 수 도 있다.
         * -> 서로 다른 스레드에 의해 호출 될 수 있는 메서드로 동기화 락을 사용함.
         *
         * 여러번 close 가 호출 될 수 있으므로 자원정리가 단 한번만 수행됨을 보장 시키기 위해 isClose 변수를 사용
         * -> 메모리 가시성 문제가 생길 수 있는데 임계 영역내에 있으므로 해결됨.
         */

        if (isClosed) {
            return;
        }

        isClosed = true;
        closeAll(); // SessionV3 모든 자원 정리

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
