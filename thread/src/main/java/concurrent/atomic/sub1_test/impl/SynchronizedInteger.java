package concurrent.atomic.sub1_test.impl;

import concurrent.atomic.sub1_test.IncrementInteger;

public class SynchronizedInteger implements IncrementInteger {

    private int value;


    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
