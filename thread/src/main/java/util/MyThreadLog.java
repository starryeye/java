package util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class MyThreadLog {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    private static final String LOG_FORMAT = "%s [%12s] %s";

    private MyThreadLog() {}

    public static void threadLog(Object object) {

        String time = LocalDateTime.now().format(FORMATTER);

        System.out.println(LOG_FORMAT.formatted(time, Thread.currentThread().getName(), String.valueOf(object)));
    }
}
