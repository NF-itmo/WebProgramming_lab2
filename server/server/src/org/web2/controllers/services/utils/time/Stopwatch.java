package org.web2.controllers.services.utils.time;

public class Stopwatch {
    private long startTime;

    public Stopwatch start() {
        this.startTime = System.nanoTime();
        return this;
    }
    public long stop() {
        return System.nanoTime() - this.startTime;
    }
}
