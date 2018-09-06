package de.nikolovi.misc.clicker.clicking;

import de.nikolovi.misc.clicker.recording.Click;
import de.nikolovi.misc.clicker.recording.Clicks;
import javafx.scene.control.Alert;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ListIterator;

public class ClickerRunnable implements Runnable {

    private volatile boolean stopFlag = false;

    private final Clicks clicks;

    public ClickerRunnable(Clicks clicks) {
        this.clicks = clicks;
    }

    public void stop() {
        stopFlag = true;
    }

    @Override
    public void run() {

        ListIterator<Click> iterator = clicks.listIterator();
        Click click = iterator.next();
        long currentDelay = 0;

        try {
            final Robot robot = new Robot();
            robot.delay(1000);

            while (!stopFlag) {
                robot.mouseMove(click.getX(), click.getY());
                robot.delay(10);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(10);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(10);

                if (!iterator.hasNext()) {
                    iterator = clicks.listIterator();
                    currentDelay = 0;

                    if (stopFlag) {
                        break;
                    }
                    robot.delay(1000);
                }

                Click next = iterator.next();
                if (stopFlag) {
                    break;
                }
                robot.delay((int)(next.getTimeOffsetMs() - currentDelay));
                currentDelay = next.getTimeOffsetMs();

                if (!interrupted(click)) {
                    click = next;
                } else {
                    stopFlag = true;
                }
            }
        } catch (AWTException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Exception");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private boolean interrupted(Click currentClick) {
        final Point currentPoint = new Point(currentClick.getX(), currentClick.getY());
        final Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        return !mouseLocation.equals(currentPoint);
    }
}
