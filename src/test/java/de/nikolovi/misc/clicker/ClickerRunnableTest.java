package de.nikolovi.misc.clicker;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.awt.*;

public class ClickerRunnableTest {

    final ClickerRunnable runnable = new ClickerRunnable(0, 10, 100, 200);

    @Test
    public void sameRow() {
        final Point current = new Point(0, 10);
        final Point nextTarget = runnable.getNextTarget(current);

        Assertions.assertThat(nextTarget.getX()).isEqualTo(1d);
        Assertions.assertThat(nextTarget.getY()).isEqualTo(10d);
    }

    @Test
    public void nextRow() {
        final Point current = new Point(99, 10);
        final Point nextTarget = runnable.getNextTarget(current);

        Assertions.assertThat(nextTarget.getX()).isEqualTo(0d);
        Assertions.assertThat(nextTarget.getY()).isEqualTo(11d);
    }

    @Test
    public void nextCycle() {
        final Point current = new Point(99, 209);
        final Point nextTarget = runnable.getNextTarget(current);

        Assertions.assertThat(nextTarget.getX()).isEqualTo(0d);
        Assertions.assertThat(nextTarget.getY()).isEqualTo(10d);
    }
}