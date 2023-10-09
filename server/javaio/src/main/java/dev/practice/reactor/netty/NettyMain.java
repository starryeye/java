package dev.practice.reactor.netty;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class NettyMain {

    public static void main(String[] args) {
        log.info("main start");
        List<EventLoop> eventLoops = List.of(new EventLoop(8080)); // EventLoop(reactor) 를 여러개 가질 수 있다.
        eventLoops.forEach(EventLoop::run);
        log.info("main end"); // main 은 바로 끝나지만, Reactor 의 싱글스레드는 계속 실행한다.
    }
}
