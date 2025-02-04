package com.vwdigitalhub.cleaningrobots.domain.valueobject;

public class RobotPosition extends Coordinate {
    public RobotPosition(String coordinateX, String coordinateY) {
        super(coordinateX, coordinateY);
    }
    public RobotPosition(Integer x, Integer y) {
        super(x, y);
    }

    @Override
    String showError() {
        return "Robot position is invalid: coordinates not valid";
    }
}
