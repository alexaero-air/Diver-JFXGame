package ru.diver.screen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.net.URL;

public class GameScreen extends StackPane{
    private static final long FRAME_DURATION_NANOS = 100_000_000L;

    private final Canvas canvas = new Canvas();
    private final Image [] idleFrame = new Image[15];
    private final AnimationTimer animationTimer;

    private int currentFrameIndex = 0;

    public GameScreen(Runnable onBack) {
        for (int i=0; i<idleFrame.length; i++){
            String path = "/images/diver/idle/" +
                    "__diver_idle_holding_hand_weapon_%03d.png".formatted(i);
            URL imageUrl = getClass().getResource(path);

            if (imageUrl == null){
                throw new IllegalStateException("Не найден ресурс: " + path);
            }

            idleFrame[i] = new Image(imageUrl.toExternalForm());
        }

//        canvas.setManaged(false);

        Button backButton = new Button("Назад в меню");
        backButton.setPrefWidth(180);
        backButton.setOnAction(event -> onBack.run());

        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(16));
        getChildren().addAll(canvas, backButton);

        animationTimer = new AnimationTimer() {
            private long startTime;
            private boolean initialized;

            @Override
            public void handle (long now){
                if (!initialized){
                    startTime = now;
                    initialized = true;
                }

                long framesPassed = (now - startTime) / FRAME_DURATION_NANOS;
                currentFrameIndex = (int) (framesPassed % idleFrame.length);

                draw();
            }

        };
        sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) animationTimer.start();
            else animationTimer.stop();
        });

    }
    @Override
    protected void layoutChildren(){
        super.layoutChildren();

        canvas.setWidth(getWidth());
        canvas.setHeight(getHeight());
        canvas.relocate(0,0);

        draw();
    }

    private void draw(){
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        if (width <= 0 || height <= 0) return;

        Image diverImage = idleFrame[currentFrameIndex];
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(Color.web("#123d52"));
        graphics.fillRect(0,0, width, height);

        double diverHeight = height * 0.3;
        double diverWidth = diverHeight * diverImage.getWidth() / diverImage.getHeight();

        double diverX = width * 0.15;
        double diverY = height * 0.85 - diverHeight;

        graphics.drawImage(
                diverImage,
                diverX,
                diverY,
                diverWidth,
                diverHeight
        );
    }
}