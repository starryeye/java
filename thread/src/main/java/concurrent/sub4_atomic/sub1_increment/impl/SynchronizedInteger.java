package concurrent.sub4_atomic.sub1_increment.impl;

import concurrent.sub4_atomic.sub1_increment.IncrementInteger;

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
