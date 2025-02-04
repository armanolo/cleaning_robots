package com.vwdigitalhub.cleaningrobots.domain;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidSquareInputException;
import com.vwdigitalhub.cleaningrobots.domain.interfaces.Observer;
import com.vwdigitalhub.cleaningrobots.domain.interfaces.Subject;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.Coordinate;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.UpperRightSquare;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Square implements Subject {
    private final UpperRightSquare upperRightSquare;
    private final List<Robot> robotList;

    public Square(String coordinates){
        try{
            String[] coordinatesArray = coordinates.trim().split("\\s+");
            this.upperRightSquare = new UpperRightSquare(coordinatesArray[0], coordinatesArray[1]);
             robotList = new ArrayList<>();
        }catch (Exception e){
            throw new InvalidSquareInputException(
                String.format("Invalid input data for upper-right square: %s",coordinates)
            );
        }
    }

    public UpperRightSquare getUpperRightSquare() {
        return upperRightSquare;
    }

    public boolean isOutOfArea(Coordinate coordinate) {
        return (
                coordinate.getX() > this.getUpperRightSquare().getX()
                        ||
                coordinate.getY() > this.getUpperRightSquare().getY()
        );
    }

    @Override
    public void addObserver(Observer observer) {
        robotList.add((Robot) observer);
    }

    @Override
    public void movementTo(Coordinate coordinate) {
        robotList.forEach(it -> it.isConflictWithCoordinate(coordinate));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(getUpperRightSquare(), square.getUpperRightSquare());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUpperRightSquare());
    }
}
