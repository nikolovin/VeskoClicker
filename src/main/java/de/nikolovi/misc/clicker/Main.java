package de.nikolovi.misc.clicker;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GlobalScreen.registerNativeHook();

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("VeskoClicker");
        primaryStage.setScene(new Scene(root, 300, 100));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    GlobalScreen.unregisterNativeHook();
                } catch (NativeHookException e) {

                }
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
