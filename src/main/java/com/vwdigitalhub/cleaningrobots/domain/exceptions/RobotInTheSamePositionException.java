package com.vwdigitalhub.cleaningrobots.domain.exceptions;

public class RobotInTheSamePositionException extends RuntimeException {
    public RobotInTheSamePositionException(String message){super(message);}
}
