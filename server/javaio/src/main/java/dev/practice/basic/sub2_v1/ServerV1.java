package dev.practice.basic.sub2_v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class ServerV1 {

    static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        threadLog("server start");


        // server 는 client 와 다르게 ServerSocket 이라는 객체도 사용한다.
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        threadLog("serverSocket created: " + serverSocket);
        /**
         * 8080 port 를 사용하여 ServerSocket 을 생성하면..
         *      client 는 server 의 IP 주소와 해당 port 를 이용하여 server 에 TCP 연결(3-way handshake)이 가능하다.
         */




        /**
         * ServerSocket 이 생성되면 client - server 간 TCP 연결이 공식적으로 가능하다.
         *      Java 프로세스가 TCP 연결을 하는 것이 아니므로 Java 프로세스의 스레드와 아무런 상관없이 연결을 할 수 있다.
         *
         * 1. client 가 Socket 을 생성하면..
         *      client - server 간 3-way handshake 를 하고 TCP 연결이 완료된다.
         * 2. client - server 간 TCP 연결이 완료되면..
         *      server 는 OS backlog queue 에 client - server 간 "연결 정보"를 보관한다.
         *      "연결 정보"는 client 와 server 의 IP 주소, port 모두이다.
         *
         * 참고.
         * ServerSocket 만 생성해도 OS 에서 Java 프로세스(Server)와 상관없이 TCP 연결을 지원하기 때문에
         *      여러 client 가 해당 server 로 TCP 연결은 얼마든지 가능하다.
         * 하지만, 이 예제에서는 하나의 스레드(main)가 accept 메서드를 통해 단 하나의 Socket 만 생성하므로..
         *      단 하나의 첫번째 client 랑만 데이터 통신이 가능하다.
         */




        // ServerSocket 은 client 와의 TCP 연결만을 위한 소캣이며, 데이터를 주고 받기 위한 Socket 은 이때 만들어진다.
        Socket socket = serverSocket.accept();
        threadLog("socket accepted: " + socket);
        /**
         * 1. ServerSocket::accept() 를 호출하면, OS backlog queue 에서 TCP 연결 정보를 조회한다.
         *      TCP 연결 정보가 존재하지 않으면, 스레드는 대기한다.
         *          이 때, Thread.State 값은 RUNNABLE 이지만, CPU 소모하지 않고, 네이티브 소켓 API를 호출해 커널 레벨에서 I/O 대기를 한다.
         *          즉, RUNNABLE 이지만, WAITING 상태 처럼 코드상 blocking 된다.
         * 2. OS backlog queue 에서 조회된 TCP 연결 정보로 Socket 객체를 생성한다.
         *      조회된 TCP 연결 정보는 OS backlog queue 에서 삭제된다.
         */



        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        /**
         * server 와 client 간의 데이터 통신은 Socket 객체가 제공하는 stream 을 이용할 수 있다.
         */





        // requested
        String received = input.readUTF();
        threadLog("client -> server: " + received);
        /**
         * client 에서 전송한 byte[] 값을 DataInputStream::readUTF 메서드를 이용하여 편리하게 server 내부 Java String 타입의 값으로 받는다.
         * client 가 보낸 데이터가 server 에 도착 할 때 까지 스레드는 대기한다.
         *      accept 와 동일하게 CPU 소모 없이, RUNNABLE 이지만, WAITING 상태 처럼 코드상 blocking 된다.
         */


        // response
        String toSend = received + " World!";
        output.writeUTF(toSend);
        threadLog("client <- server: " + toSend);
        /**
         * Java String 타입의 값을 DataOutputStream::writeUTF 메서드를 이용하여 편리하게 client 로 전송한다.
         */



        // 자원 정리
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
        threadLog("resource closed: " + socket);
        /**
         * 사용한 socket, input/output stream 자원을 정리한다.
         */


        threadLog("server end");
    }
}
