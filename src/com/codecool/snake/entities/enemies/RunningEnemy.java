package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;


public class RunningEnemy extends EnemyBasic implements Animatable, Interactable {

    public RunningEnemy() {
        super(30, "RunningEnemy", 90, 10);
    }
}
