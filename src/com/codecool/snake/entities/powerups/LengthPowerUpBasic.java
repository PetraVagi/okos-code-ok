package com.codecool.snake.entities.powerups;

public class LengthPowerUpBasic extends PowerUpBasic {
    public LengthPowerUpBasic() {
        super("PowerUpBerry");
    }

    @Override
    public String getMessage() {
        return "Got food :)";
    }
}
