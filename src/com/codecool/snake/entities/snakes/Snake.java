package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;

import java.util.Objects;


public class Snake implements Animatable {
    public static int counter = 1;
    private String name;
    private static final float startSpeed = 2;
    private float currentSpeed = startSpeed;
    private int health = 100;

    private static boolean isTheFirstSnakeLive = true;
    private static boolean isTheSecondSnakeLive = true;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        this.name = createNameForSnake();

        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, currentSpeed);

        for (int i = 0; i < currentSpeed/2; i++) {
            updateSnakeBodyHistory();

        }

        checkForGameOverConditions();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(Objects.equals(this.name, "snake_1")){
            if(InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
            if(InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        }
        if(Objects.equals(this.name, "snake_2")){
            if(InputHandler.getInstance().isKeyPressed(KeyCode.A)) turnDir = SnakeControl.TURN_LEFT;
            if(InputHandler.getInstance().isKeyPressed(KeyCode.D)) turnDir = SnakeControl.TURN_RIGHT;
        }
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public void changeCurrentSpeed(int diff) {
        currentSpeed += diff;
    }

    private void destroySnake(){
        body.getList().forEach(GameEntity::destroy);
        head.destroy();
    }

    private void checkForGameOverConditions() {
        /*Snake death*/

        if (Objects.equals(this.name, "snake_1") && (head.isOutOfBounds() || health <= 0)) {
                isTheFirstSnakeLive = false;
                destroySnake();
            }
        if (Objects.equals(this.name, "snake_2") && (head.isOutOfBounds() || health <= 0)) {
                isTheSecondSnakeLive = false;
                destroySnake();
        }

        /*Game Over*/

        if (!isTheFirstSnakeLive && !isTheSecondSnakeLive){
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            isTheFirstSnakeLive = true;
            isTheSecondSnakeLive = true;
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }

    private String createNameForSnake(){
        String name = "snake_" + counter;
        counter++;
        return name;
    }
}
