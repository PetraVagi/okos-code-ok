package com.codecool.snake.entities.powerups;

public class SpeedPowerUp extends SimplePowerUp {
    public SpeedPowerUp() {
        super("PowerUpFlash");
    }

    @Override
    public String getMessage() {
        return "Got speed :)";
    }
}
