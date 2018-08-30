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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fieldX.setEditable(true);
        fieldY.setEditable(true);
        fieldWidth.setEditable(true);
        fieldHeight.setEditable(true);
    }

    @FXML protected void handleStartButtonAction(ActionEvent event) {

    }

    @FXML protected void handleStopButtonAction(ActionEvent event) {
        
    }
}
