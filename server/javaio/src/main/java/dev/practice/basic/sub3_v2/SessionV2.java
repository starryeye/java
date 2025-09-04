package dev.practice.basic.sub3_v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class SessionV2 implements Runnable {

    private final Socket socket;

    public SessionV2(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        /**
         * try with resources
         *      아래의 "socket" 참조 변수 같이 외부에서 할당된 자원도 try 내부에 표기해주면 자원 정리 대상에 포함시킬 수 있다.
         */
        try(socket;
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

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
        }

        threadLog("connection closed : " + socket + ", Socket::isClosed() : " + socket.isClosed());
    }
}
