package dev.practice.basic.sub5_exception.connect_close.unnormal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class NormalCloseServer {

    /**
     * server 는 정상 종료
     */

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        Socket socket = serverSocket.accept();
        threadLog("socket accepted : " + socket);

        socket.close(); // server 가 client 로 FIN 패킷 전달
        threadLog("socket closed : " + socket + ", isClosed : " + socket.isClosed());
    }
}
