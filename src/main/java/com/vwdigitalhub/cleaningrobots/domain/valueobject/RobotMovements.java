package com.vwdigitalhub.cleaningrobots.domain.valueobject;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidMovementLetterException;

import java.util.List;
import java.util.Objects;

public class RobotMovements {

    private final List<MovementLetter> movementLetterList;

    public RobotMovements(String movements){

        if (movements == null ){
            throw new InvalidMovementLetterException("Not movements for the robot");
        }

        movementLetterList = movements.chars().mapToObj(
                letter -> MovementLetter.fromValue(String.valueOf((char) letter))
        ).toList();
    }

    public List<MovementLetter> getMovementLetterList() {
        return movementLetterList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RobotMovements that = (RobotMovements) o;
        return Objects.equals(getMovementLetterList(), that.getMovementLetterList());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMovementLetterList());
    }
}
