package com.loongson.debug.util;

public class RunTimer {
    private long start;
    private long end;
    private boolean printText;

    public RunTimer(boolean printText) {
        this.printText = printText;
    }

    public void start() {
        this.start = System.currentTimeMillis();
    }

    public void end(String text) {
        this.end = System.currentTimeMillis();
        if (printText) {
            System.out.println("[" + text + "\t]用时:" + (end - start) + "ms.");
        }
    }

    public void end(String text, boolean show) {
        this.end = System.currentTimeMillis();
        if (show) {
            System.out.println("[" + text + "\t]用时:" + (end - start) + "ms.");
        }
    }


}
