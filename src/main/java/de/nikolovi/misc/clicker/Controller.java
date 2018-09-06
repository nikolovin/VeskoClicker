package de.nikolovi.misc.clicker;

import de.nikolovi.misc.clicker.clicking.ClickerRunnable;
import de.nikolovi.misc.clicker.recording.ClickRepository;
import de.nikolovi.misc.clicker.recording.Recorder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import org.jnativehook.GlobalScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final Object lock = new Object();

    @FXML
    private Button btnRecord;

    @FXML
    private Button btnClick;

    private Thread clickerThread;
    private ClickerRunnable clickerRunnable;
    private Recorder recorder = new Recorder();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GlobalScreen.setEventDispatcher(new JavaFxDispatchService());
    }

    @FXML
    protected void handleStartButtonAction(ActionEvent event) {
        synchronized (lock) {
            stopClicking();

            ClickRepository repo = new ClickRepository();

            try {
                clickerRunnable = new ClickerRunnable(repo.load());
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Exception");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
            clickerThread = new Thread(clickerRunnable);
            clickerThread.start();
        }
    }

    private void stopClicking() {
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
    }

    @FXML
    protected void handleRecordButtonAction(ActionEvent event) {
        synchronized (lock) {
            if (btnRecord.getUserData().equals("1")) {
                //stop current clicking

                btnRecord.setUserData("2");
                btnRecord.setText("STOP RECORDING");
                btnClick.setDisable(true);

                recorder.start();
            } else {
                try {
                    recorder.stop();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Exception");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

                btnRecord.setUserData("1");
                btnRecord.setText("START RECORDING");
                btnClick.setDisable(false);
            }
        }
    }
}
