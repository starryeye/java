package dev.practice.basic.sub5_exception.connect_close;

public interface ExceptionOfConnectClose {

    /**
     * TCP 연결 종료에 관한 예외에 대해 알아본다.
     *
     * TCP 연결 종료 (4 way handshake)
     * <- fin
     * ack ->
     * fin + ack ->
     * <- ack
     *
     * 정상 종료 과정
     * 0. client 가 server 로 요청 데이터를 보내고 server 는 client 로 응답 데이터를 보냄
     * 1. server 가 TCP 연결 종료를 위해 socket.close() 를 호출
     *      server 는 client 에 FIN 패킷 전달
     * 2. client 는 FIN 패킷을 받는다.
     *      client 의 OS 는 FIN 에 대한 ACK 패킷을 server 로 전달
     * 3. client application 입장에서는 응답 데이터를 쭉 받다가 마지막에 EOF 에 의한 리턴값을 받는다.
     *      client 는 socket.close() 를 호출
     *      client 는 server 에 FIN 패킷 전달
     * 4. server 는 FIN 패킷을 받는다.
     *      server 의 OS 는 FIN 에 대한 ACK 패킷을 client 로 전달
     *
     * 참고
     * client 와 server 중 누가 처음으로 FIN 을 전달해야한다는 규칙은 없다.
     *      여기서는 server 가 client 요청에 대한 응답을 보내고 바로 FIN 을 처음으로 보낸다고 가정하고 설명함
     * 또한, client 는 server 가 보낼 FIN 패킷을 기다렸다가 닫는 게 아니고, 원하는 시점에 닫을 수 있다.
     */
}
