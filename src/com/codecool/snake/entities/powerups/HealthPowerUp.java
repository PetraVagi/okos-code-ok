package com.codecool.snake.entities.powerups;

public class HealthPowerUp extends SimplePowerUp {
    public HealthPowerUp() {
        super("PowerUpHeart");
    }

    @Override
    public String getMessage() {
        return "Got health :)";
    }
}
