package com.codecool.snake.entities.powerups;

public class HealthPowerUpBasic extends PowerUpBasic {
    public HealthPowerUpBasic() {
        super("PowerUpHeart");
    }

    @Override
    public String getMessage() {
        return "Got health :)";
    }
}
