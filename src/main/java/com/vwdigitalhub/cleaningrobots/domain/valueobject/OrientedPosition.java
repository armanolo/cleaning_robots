package com.vwdigitalhub.cleaningrobots.domain.valueobject;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidRobotInputException;

public class OrientedPosition {

    private final RobotPosition robotPosition;
    private final Cardinal cardinal;

    public OrientedPosition(String input){

        String[] inputDataList = splitTokenInput(input);
        robotPosition = new RobotPosition(inputDataList[0], inputDataList[1]);
        cardinal = Cardinal.fromValue(inputDataList[2]);
    }

    private String[]  splitTokenInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidRobotInputException("Input cannot be null or empty.");
        }
        String[] parts = input.trim().split("\\s+");
        if (parts.length != 3) {
            throw new InvalidRobotInputException("Input have to be split in: x y cardinal");
        }

        return parts;
    }

    public Cardinal getCardinal() {
        return cardinal;
    }

    public RobotPosition getRobotPosition() {
        return robotPosition;
    }

}
