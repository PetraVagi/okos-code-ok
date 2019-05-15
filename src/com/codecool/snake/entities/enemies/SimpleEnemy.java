package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;

import java.util.Random;



public class SimpleEnemy extends EnemyBasic implements Animatable, Interactable {

    private static Random rnd = new Random();

    public SimpleEnemy() {

        super(10, "SimpleEnemy", rnd.nextDouble()*360, 2);
    }
}
