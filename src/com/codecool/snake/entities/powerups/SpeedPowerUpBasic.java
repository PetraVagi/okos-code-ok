package com.codecool.snake.entities.powerups;

public class SpeedPowerUpBasic extends PowerUpBasic {
    public SpeedPowerUpBasic() {
        super("PowerUpFlash");
    }

    @Override
    public String getMessage() {
        return "Got speed :)";
    }
}
