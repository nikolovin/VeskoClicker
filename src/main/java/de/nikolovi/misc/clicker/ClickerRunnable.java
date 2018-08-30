package de.nikolovi.misc.clicker;

import javafx.scene.control.Alert;

import java.awt.*;
import java.awt.event.InputEvent;

public class ClickerRunnable implements Runnable {

    private volatile boolean stopFlag = false;

    private final Rectangle bounds;
    private Point currentPoint;

    public ClickerRunnable(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void stop() {
        stopFlag = true;
    }

    @Override
    public void run() {

        currentPoint = new Point((int)bounds.getMinX(), (int)bounds.getMinY());

        try {
            final Robot robot = new Robot();
            robot.delay(1000);

            while (!stopFlag) {
                robot.mouseMove((int)currentPoint.getX(), (int)currentPoint.getY());
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                if (!interrupted(currentPoint)) {
                    currentPoint = getNextTarget(currentPoint);
                    if (isCycleEnded(currentPoint)) {
                        robot.delay(1000);
                    }
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

    private boolean isCycleEnded(Point currentPoint) {
        return currentPoint.getX() == bounds.getMinX() && currentPoint.getY() == bounds.getMinY();
    }

    private boolean interrupted(Point currentPoint) {
        final Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        return !mouseLocation.equals(currentPoint);
    }

    Point getNextTarget(Point currentPoint) {
        int x = currentPoint.getX() < bounds.getMaxX() - 1 ? (int)currentPoint.getX() + 1 : (int)bounds.getMinX();
        int y = (int)currentPoint.getY();
        if (x == (int)bounds.getMinX()) {
            y = currentPoint.getY() < bounds.getMaxY() - 1 ? (int) currentPoint.getY() + 1 : (int)bounds.getMinY();
        }
        return new Point(x, y);
    }
}
