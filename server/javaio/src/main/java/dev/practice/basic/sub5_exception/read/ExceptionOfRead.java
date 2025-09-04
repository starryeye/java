package dev.practice.basic.sub5_exception.read;

public interface ExceptionOfRead {

    /**
     * socket timeout (read timeout) 에 대해 알아본다.
     *
     * TCP 연결 이후, client 가 server 로부터 데이터가 오기를 대기하는데,
     * 서버는 모종의 이유로 데이터를 보내지 않는 상황이다.
     * client 가 특정 시간 만큼 응답을 대기하다가 발생하는 예외이다. SocketTimeoutException
     *
     * socket timeout (read timeout) 을 설정하지 않으면, 기본적으로 무기한 대기하기 때문에 항상 신경써주자
     */
}
