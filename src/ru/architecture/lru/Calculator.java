package ru.architecture.lru;

public class Calculator {
    private final int offset;

    public Calculator(int offset) {
        this.offset = offset;
    }

    //some difficult calculations
    public int calculate(int x) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100*x + offset;
    }
}
