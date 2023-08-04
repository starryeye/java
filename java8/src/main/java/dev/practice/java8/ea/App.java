package dev.practice.java8.ea;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {

    /**
     * Java 8 에 새로 등장한 시간과 날짜를 다루는 API
     */

    /**
     * 자바 8에 새로운 날짜와 시간 API가 생긴 이유
     * - 그전까지 사용하던 java.util.Date 클래스는 mutable 하기 때문에 thead safe 하지 않다.
     * - 클래스 이름이 명확하지 않다. Date인데 시간까지 다룬다.
     * - 버그 발생할 여지가 많다. (타입 안정성이 없고, 월이 0부터 시작한다거나..)
     * - 날짜 시간 처리가 복잡한 애플리케이션에서는 보통 Joda Time을 쓰곤했다.
     *
     * 자바 8에서 제공하는 Date-Time API
     * - JSR-310 스팩의 구현체를 제공한다.
     * - 디자인철학
     * - - Clear : API 명만 봐도 뭘하는지 명확
     * - - Fluent : 메서드 체인을 지원
     * - - Immutable : 불변 객체
     * - - Extensible : 일반적으로 사용하는 달력외에 다양한 달력(ex. 불교달력)을 지원하고 개발자가 새로운 달력을 만들수 있다.
     *
     * 자바 8에서 제공하는 Date-Time 주요 API
     * - 기계용 시간 (machine time)과 인류용 시간(human time)으로 나눌 수 있다.
     * - 기계용 시간은 EPOCK (1970년 1월 1일 0시 0분 0초)부터 현재까지의 타임스탬프를 표현한다. (epoch time, 유닉스 시간)
     * - 인류용 시간은 우리가 흔히 사용하는 연,월,일,시,분,초 등을 표현한다.
     * - 타임스탬프는 Instant 를 사용한다.
     * - 특정 날짜(LocalDate), 시간(LocalTime), 일시(LocalDateTime)를 사용할 수 있다.
     * - 기간을 표현할 때는 Duration (시간 기반)과 Period (날짜 기반)를 사용할 수 있다.
     * - DateTimeFormatter를 사용해서 일시를 특정한 문자열로 포매팅할 수 있다.
     *
     * 참고
     * - https://codeblog.jonskeet.uk/2017/04/23/all-about-java-util-date/
     * - - Java 8 이전의 Date, Calendar, SimpleDateFormat 등 의 불편한 점을 포스팅
     * - https://docs.oracle.com/javase/tutorial/datetime/overview/index.html
     * - https://docs.oracle.com/javase/tutorial/datetime/iso/overview.htm-
     */

    public static void main(String[] args) throws InterruptedException {

        /**
         * 아래는 Java 8 이전의 불편한 점이다.
         */

        /**
         * Date
         */
        Date date1 = new Date();
        System.out.println(date1);

        // -> Date 이지만 시간까지 다룬다.
        long time = date1.getTime(); // 유닉스 시간, epoch time 을 반환해준다.
        System.out.println(time);

        // --

        // 시간을 변경할 수 있다..
        Date date2 = new Date();
        System.out.println(date2);
        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);
        after3Seconds.setTime(date2.getTime());
        System.out.println(after3Seconds);
        // 변경 가능하므로 mutable 한 것이다. 여러 스레드가 동시 접근(수정)하면 변경에 대해 Thread-safe 하지 않다.(race condition)
        // 그래서 mutable 한 객체를 다룰 때는 동시성 문제를 고려해야한다.


        // --

        //Type-safe 하지 않다.
        Calendar calendar1 = new GregorianCalendar(1993, 1, 11);
        // -> 1 이 1월이 아니라 2월이다. (Calendar.FEBRUARY 로 사용하자)
        // -> 달은 1월 부터 12월 까지 있지만 int 형으로 받고 있다. (int 형 말고 Month 라는 타입을 만들어 받게 해야한다.)

        // --

        // Date 에서 getTime 을 하면 유닉스 시간이 나오고.. Calendar 에서 getTime 을 하면 Date 가 나옴...
        System.out.println(calendar1.getTime());

        // --

        /**
         * Calendar
         */
        // Calendar 도 mutable 이다.
        calendar1.add(Calendar.DAY_OF_YEAR, 1);
        // -> Java 8 에서는 immutable 하다. (시간을 더하면 새로운 객체가 만들어져서 리턴될 것이다. String 객체 처럼..)

        // 그래서, Java 8 이전에는.. Joda-Time 이라는 날짜/시간 관련 라이브러리를 사용하였다...



        // 얘도 Java 8 이전..
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    }
}
