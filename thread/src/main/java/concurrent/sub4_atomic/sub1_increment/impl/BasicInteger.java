package concurrent.sub4_atomic.sub1_increment.impl;

import concurrent.sub4_atomic.sub1_increment.IncrementInteger;

public class BasicInteger implements IncrementInteger {

    private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
