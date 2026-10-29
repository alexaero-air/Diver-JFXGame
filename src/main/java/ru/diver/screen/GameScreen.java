package ru.diver.screen;

import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import java.awt.*;

public class GameScreen extends StackPane{
    public GameScreen(){
        Label title = new Label("Игровой экран");
        title.setStyle(
                "-fx-font-size: 36px; -fx-text-fill: white;"
        );
    }

}
