package dev.practice.basic.sub1_dns;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Dns {

    /**
     * TCP/IP 통신에서 통신할 대상 서버를 찾을 때는 "호스트 이름"을 통해 "IP 주소"를 알아내야한다.
     *
     * Java 의 InetAddress 클래스를 사용하면 "호스트 이름"으로 대상 "IP 주소"를 알아낼 수 있다.
     *
     * InetAddress::getByName(String) 을 호출하면..
     * 1. 시스템의 호스트 파일을 확인($ /etc/hosts)
     * 2. 호스트 파일에 존재하지 않으면, DNS 서버에 요청해서 IP 주소를 얻는다.
     *
     * localhost(127.0.0.1)..
     *      현재 사용 중인 컴퓨터 자체를 가리키는 "호스트 이름"이다.
     *      localhost 는 127.0.0.1 IP 로 매핑된다.
     *      127.0.0.1 는 IP 주소체계에서 loopback address 로 지정된 특별한 IP 주소이다.
     *          컴퓨터가 네트워크 인터페이스를 통해 외부로 나가도록 하지 않고, 자신에게 직접 네트워크 패킷을 보낼 수 있도록 한다.
     */

    public static void main(String[] args) throws UnknownHostException {

        // InetAddress::getLocalHost 로 localhost 에 해당하는 IP 주소를 조회
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println(localhost);

        // InetAddress::getByName(String) 로 String 에 해당하는 IP 주소를 조회
        InetAddress google = InetAddress.getByName("google.com");
        System.out.println(google);
    }
}
