package com.vwdigitalhub.cleaningrobots.domain.valueobject;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidCoordinatesException;

import java.util.Objects;

public abstract class Coordinate {

    private int x = 0;
    private int y = 0;

    protected Coordinate(String coordinateX, String coordinateY) {

        try {
            this.x = Integer.parseInt(coordinateX);
            this.y = Integer.parseInt(coordinateY);
        }catch (Exception e){
            throw new InvalidCoordinatesException(showError());
        }
        validNegativeValues(x, y);
    }

    protected Coordinate(int x, int y) {
        validNegativeValues(x, y);
        this.x = x;
        this.y = y;
    }

    private void validNegativeValues(int x, int y){
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be non-negative");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("%d%d",x,y);
    }

    abstract String showError();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return getX() == that.getX() && getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
