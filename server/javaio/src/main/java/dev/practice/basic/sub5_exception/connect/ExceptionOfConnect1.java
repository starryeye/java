package dev.practice.basic.sub5_exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ExceptionOfConnect1 {

    /**
     * 네트워크 TCP "연결"과 관련된 예외
     *
     * 3 way handshake
     * syn ->
     * <- syn + ack
     * ack ->
     */

    public static void main(String[] args) throws IOException {

        unknownHost1();         // UnknownHostException
        unknownHost2();         // UnknownHostException
        connectionRefused();    // ConnectException
    }

    private static void unknownHost1() throws IOException {

        // TCP 연결을 위해 3 way handshake 시도 하였지만, 잘못된 IP 라 파싱 실패
        // 0 ~ 255 범위를 벗어나므로 파싱 실패
        try {
            Socket socket = new Socket("999.999.999.999", 80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void unknownHost2() throws IOException {

        // TCP 연결을 위해 3 way handshake 시도 하였지만, 존재하지 않는 "호스트 이름" 이라 예외 발생
        // DNS 서버에서 못찾음
        try {
            Socket socket = new Socket("google.gogo", 80);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void connectionRefused() throws IOException {

        /**
         * TCP 연결을 위해 3 way handshake 시도..
         *      연결이 거절됨.
         *      IP 는 존재하여, 서버에 접속은 했지만 사용되고 있는 Port 가 없을 때 발생한다.
         *      서버의 OS 가 TCP RST 패킷을 클라이언트로 보낸다.
         *      클라이언트는 TCP RST 패킷을 받으면 ConnectException 예외가 발생한다.
         */
        try {
            Socket socket = new Socket("localhost", 45678);
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }
}
