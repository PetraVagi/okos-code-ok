package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;


public class FlyingEnemy extends EnemyBasic implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();
    private double direction = 0;
    private int speed = Utils.generateRandomBetween(1, 6);


    public FlyingEnemy() {
        super(20, "FlyingEnemy", 0, 1);
    }

    private void changeDirection() {direction += 1;}

    @Override
    public void step() {

        changeDirection();
        setRotate(direction);

        heading = Utils.directionToVector(direction, speed);

        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

}
