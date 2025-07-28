package concurrent.atomic.sub1_test.impl;

import concurrent.atomic.sub1_test.IncrementInteger;

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
