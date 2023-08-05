package dev.practice.java8.eb;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {

    /**
     * Java 8 Date/Time 대표적 API
     */

    public static void main(String[] args) {

        // epoch time 을 다루기
        Instant instant = Instant.now(); // 혹은 Instant.of 로 다룰 수 있다.
        System.out.println(instant); //출력은 우리가 아는 형식의 시간으로 출력이 된다... (UTC, GMT, 기준시로 출력)
        System.out.println(instant.atZone(ZoneId.of("UTC"))); // 기준시로 출력, 위와 동일한 값

        ZoneId zoneId = ZoneId.systemDefault(); // 지금 시스템 기준..
        System.out.println(zoneId);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        System.out.println(zonedDateTime);

        System.out.println(instant.atZone(ZoneId.of("America/New_York"))); // 특정 zone 기준..


        // 사람용 date/time 다루기
        LocalDateTime today = LocalDateTime.now(); // Local : 현재 시스템 zone 을 참고하여 로컬 시간을 가져온다. (서버로 띄우면 서버의 local 시간이 뜬다.)
        System.out.println(today);

        LocalDateTime birthDay = LocalDateTime.of(1993, 1, 1, 0, 0, 0); // of 사용 가능

        ZonedDateTime nowInNewYork = ZonedDateTime.now(ZoneId.of("America/New_York")); // 특정 Zone 의 현재 시간을 보고 싶다.
        System.out.println(nowInNewYork);


        // 변환 가능
        ZonedDateTime toZone = instant.atZone(ZoneId.systemDefault());
        toZone.toInstant();
        toZone.toLocalDateTime();


        // 날짜 비교 가능 (사람 용) , Period
        LocalDate today1 = LocalDate.now(); // 2023, 8, 4
        System.out.println(today1);
        LocalDate birthDayOf2024 = LocalDate.of(2024, Month.JANUARY, 11);
        System.out.println(birthDayOf2024);

        Period period = Period.between(today1, birthDayOf2024);
        System.out.println(period.getDays()); // 5개월 하고도 7일이 남았지만 출력은 7로 출력된다.

        Period until = today1.until(birthDayOf2024);
        System.out.println(until.get(ChronoUnit.DAYS)); // 5개월 하고도 7일이 남았지만 출력은 7로 출력된다.


        // 날짜 비교 가능 (epoch time 용) , Duration
        Instant instant1 = Instant.now();
        Instant plus = instant1.plus(10, ChronoUnit.SECONDS); // 참고로 Immutable 하기 때문에 좌측 연산만으로는 아무일도 일어나지 않는다. 반환 값으로 뭔가 하자..

        Duration duration = Duration.between(instant1, plus);
        System.out.println(duration.getSeconds());


        // 문자열로 날짜를 입력 받거나 출력하거나.. DateTimeFormatter
        // LocalDateTime 을 문자열로 변환
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME; //DateTime 포맷.. 이것 말고도 많다. https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#predefined
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");// 개발자가 포맷을 직접 만들어 줄 수 도 있다.  https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        System.out.println(localDateTime.format(MMddyyyy));

        // 문자열을 LocalDate 으로.. 변환
        LocalDate parse = LocalDate.parse("01/11/1993", MMddyyyy);
        System.out.println(parse);

        // 참고로 현재 문자열에 시간이 없는데 LocalDateTime 으로 변환 하려고 시도하면 에러가 발생한다.
        // 따라서 아래와 같이 시간을 추가 해줘서 LocalDateTime 을 얻을 수 있다.
        LocalDateTime localDateTime1 = parse.atStartOfDay();


        // 문자열을 LocalDateTime 으로.. 변환
        DateTimeFormatter MMddyyyyHHmm = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        LocalDateTime parse1 = LocalDateTime.parse("01/11/1993 13:25", MMddyyyyHHmm);
        System.out.println(parse1);


        // 레거시 API 와 호환이 된다.
        // Date <-> Instant
        Date date = new Date();
        Instant instant2 = date.toInstant(); // Date -> Instant
        Date from = Date.from(instant2); // Instant -> Date

        // GregorianCalendar <-> LocalDateTime (사실 뭐든 간에 Instant 로 변경하면 Instant 에서 Java 8 Date/Time 으로 다 바꿀수 있다.)
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime localDateTime2 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); // GregorianCalendar -> LocalDateTime
        GregorianCalendar from1 = GregorianCalendar.from(localDateTime2.atZone(ZoneId.systemDefault())); // GregorianCalendar <- LocalDateTime

        // ZoneId (Java 8) <-> TimeZone (legacy)
        ZoneId zoneId1 = TimeZone.getTimeZone("PST").toZoneId(); // TimeZone -> ZoneId
        TimeZone timeZone = TimeZone.getTimeZone(zoneId1); // ZoneId -> TimeZone
    }
}
