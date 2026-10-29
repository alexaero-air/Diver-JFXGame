package ru.diver;

import javafx.application.Application;
import javafx.scene.Scene;
import ru.diver.screen.MainMenuScreen;
import javafx.stage.Stage;

public class DiverApplication extends Application{
    @Override
    public void start(Stage stage){
        MainMenuScreen root = new MainMenuScreen();
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Diver");
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(450);
        stage.setResizable(true);

        stage.show();
    }
}
