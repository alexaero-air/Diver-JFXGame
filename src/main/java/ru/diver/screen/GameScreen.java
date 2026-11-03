package ru.diver.screen;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.animation.AnimationTimer;

import java.awt.*;

public class GameScreen extends StackPane{
    private static final long FRAME_DURATION_NANOS = 100_000_000L;

    private final Canvas canvas = new Canvas();
    private final Image [] idleFrame = new Image[15];
    private final AnimationTimer animationTimer;

    private int currentFrameIndex = 0;

    public GameScreen(Runnable onBack) {
        for (int i=0; i<idleFrame.length; i++){
            String path = "" + "".formatted(i);
        }
    }
}