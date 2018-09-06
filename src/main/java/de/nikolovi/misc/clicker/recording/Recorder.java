package de.nikolovi.misc.clicker.recording;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.io.IOException;

public class Recorder implements NativeMouseInputListener {

    private final ClickRepository repository;

    private long startTime;
    private Clicks clicks;

    public Recorder() {
        this.repository = new ClickRepository();
    }

    public void start() {
        clicks = new Clicks();
        GlobalScreen.addNativeMouseListener(this);
    }

    public void stop() throws IOException {
        GlobalScreen.removeNativeMouseListener(this);
        this.startTime = 0;
        clicks.pollLast();
        repository.save(clicks);
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }

        clicks.add(new Click(nativeMouseEvent.getX(), nativeMouseEvent.getY(),
                System.currentTimeMillis() - startTime));
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {

    }
}
