package com.codecool.snake.entities.powerups;

public class LengthPowerUp extends SimplePowerUp {
    public LengthPowerUp() {
        super("PowerUpBerry");
    }

    @Override
    public String getMessage() {
        return "Got food :)";
    }
}
