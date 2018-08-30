package de.nikolovi.misc.clicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Spinner<Integer> fieldX;

    @FXML
    private Spinner<Integer> fieldY;

    @FXML
    private Spinner<Integer> fieldWidth;

    @FXML
    private Spinner<Integer> fieldHeight;

    private Thread clickerThread;
    private ClickerRunnable clickerRunnable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fieldX.setEditable(true);
        fieldY.setEditable(true);
        fieldWidth.setEditable(true);
        fieldHeight.setEditable(true);

        fieldX.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                fieldX.increment(0); // won't change value, but will commit editor
            }
        });

        fieldY.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                fieldY.increment(0); // won't change value, but will commit editor
            }
        });

        fieldWidth.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                fieldWidth.increment(0); // won't change value, but will commit editor
            }
        });

        fieldHeight.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                fieldHeight.increment(0); // won't change value, but will commit editor
            }
        });
    }

    @FXML
    protected void handleStartButtonAction(ActionEvent event) {
        if (clickerThread != null && clickerRunnable != null) {
            try {
                clickerRunnable.stop();
                clickerThread.join(1000);
            } catch (InterruptedException e) {
                clickerThread.interrupt();
                clickerThread = null;
                clickerRunnable = null;
                Thread.currentThread().interrupt();
            }
        }

        clickerRunnable = new ClickerRunnable(fieldX.getValue(), fieldY.getValue(), fieldWidth.getValue(), fieldHeight.getValue());
        clickerThread = new Thread(clickerRunnable);
        clickerThread.start();
    }
}
