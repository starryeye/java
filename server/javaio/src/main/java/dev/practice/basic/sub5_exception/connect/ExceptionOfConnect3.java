package dev.practice.basic.sub5_exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ExceptionOfConnect3 {

    /**
     * 네트워크 TCP "연결"과 관련된 "타임아웃" 예외
     *
     * 과정
     * 1. IP 주소로 직접 TCP 연결을 시도함
     *      호스트 이름으로 연결시도 했다면 DNS 서버에서 찾을 수 없어서 UnknownHostException 발생했을듯..
     * 2. syn 에 대한 응답이 오지 않음
     * 3. client 의 OS 마다 특정 대기 시간이 지나면  ConnectException: Operation timed out 발생
     *      window : 약 21 초
     *      linux : 약 75 ~ 180 초
     *      mac : 약 75 초
     *
     * syn 에 대한 응답이 오지 않는 경우
     *      1. 위 과정과 같이 IP 주소로 직접 TCP 연결을 시도 했지만, IP 주소에 해당하는 서버가 없음
     *      2. 해당 IP 에 대한 서버가 존재하지만, 서버가 바쁘거나 문제가 있어서 응답이 없는 경우
     *
     * 위 과정에서 기본값이 아닌 개발자가 직접 설정한 시간만큼 대기하도록 해본다.
     * 직접 설정한 대기시간이 초과되면, ConnectException 이 아닌 SocketTimeoutException 이 발생한다.
     */
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        try {
            Socket socket = new Socket(); // TCP 연결 3 way handshake 시도 하지 않음.
            socket.connect( // TCP 연결 3 way handshake 시도 함.
                    new InetSocketAddress("192.168.1.250", 45678),
                    1000 // TCP 연결 3 way handshake 대기 시간
            );
        } catch (SocketTimeoutException e) { // java.net.SocketTimeoutException: Connect timed out
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("lapsed time = " + (endTime - startTime));
    }
}
