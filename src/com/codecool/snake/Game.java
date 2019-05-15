package com.codecool.snake;

import com.codecool.snake.entities.enemies.FlyingEnemy;
import com.codecool.snake.entities.enemies.RunningEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthPowerUpBasic;
import com.codecool.snake.entities.powerups.LengthPowerUpBasic;
import com.codecool.snake.entities.powerups.SpeedPowerUpBasic;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;


public class Game extends Pane {
    private Snake snake = null;
    private Snake snake_2 = null;
    private GameTimer gameTimer = new GameTimer();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }
    private void initRestartButton(){
        /*Initializing*/
        Button restartButton = new Button("RESTART");
        restartButton.setLayoutX(10);
        restartButton.setLayoutY(10);
        DropShadow shadow = new DropShadow();
        restartButton.setEffect(shadow);
        getChildren().add(restartButton);
        getStylesheets().add("com/codecool/snake/MainStyle.css");
        /*Event handling*/
        restartButton.setOnAction((ActionEvent ae)-> restart());
    }

    private void restart(){
        Globals.getInstance().display.clear();
        Globals.getInstance().stopGame();
        Snake.counter = 1;
        init();
        start();
    }

    public void init() {
        initRestartButton();
        spawnSnakes();
        spawnSimpleEnemies(4);
        spawnLengthPowerUps(4);
        spawnHealthPowerUps(3);
        spawnSpeedPowerUps(2);

        GameLoop gameLoop = new GameLoop(snake, snake_2);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnakes() {
        snake = new Snake(new Vec2d(500, 500));
        snake_2 = new Snake(new Vec2d(250, 500));
    }


    void spawnSimpleEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    void spawnRunningEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new RunningEnemy();
    }

    void spawnFlyingEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new FlyingEnemy();
    }

    void spawnLengthPowerUps(int numberOfLengthPowerUps) {
        for (int i = 0; i < numberOfLengthPowerUps; ++i) new LengthPowerUpBasic();
    }

    void spawnHealthPowerUps(int numberOfHealthPowerUps){
        for (int i = 0; i < numberOfHealthPowerUps; ++i) new HealthPowerUpBasic();
    }

    void spawnSpeedPowerUps(int numberOfSpeedPowerUps){
        for(int i = 0; i < numberOfSpeedPowerUps; ++i) new SpeedPowerUpBasic();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
