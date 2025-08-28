package dev.practice.advance.nio.reactor.http;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class main {

    public static void main(String[] args) {
        log.info("main start");
        Reactor reactor = new Reactor(8080);
        reactor.run();
        log.info("main end"); // main 은 바로 끝나지만, Reactor 의 싱글스레드는 계속 실행한다.
    }
}
