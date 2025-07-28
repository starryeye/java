package concurrent.atomic.sub1_test.impl;

import concurrent.atomic.sub1_test.IncrementInteger;

public class VolatileInteger implements IncrementInteger {

    private volatile int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
