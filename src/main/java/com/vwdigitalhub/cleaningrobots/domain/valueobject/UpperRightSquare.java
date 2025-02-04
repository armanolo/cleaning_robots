package com.vwdigitalhub.cleaningrobots.domain.valueobject;

public class UpperRightSquare extends Coordinate {
    public UpperRightSquare(String coordinateX, String coordinateY) {
        super(coordinateX, coordinateY);
    }

    @Override
    String showError() {
        return "Upper-right square is invalid";
    }
}
