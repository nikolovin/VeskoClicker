package de.nikolovi.misc.clicker.recording;

import java.io.Serializable;

public class Click implements Serializable {
    private int x;
    private int y;
    private long timeOffsetMs;

    public Click() {
    }

    public Click(int x, int y, long timeOffsetMs) {
        this.x = x;
        this.y = y;
        this.timeOffsetMs = timeOffsetMs;
    }

    public long getTimeOffsetMs() {
        return timeOffsetMs;
    }

    public void setTimeOffsetMs(long timeOffsetMs) {
        this.timeOffsetMs = timeOffsetMs;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
