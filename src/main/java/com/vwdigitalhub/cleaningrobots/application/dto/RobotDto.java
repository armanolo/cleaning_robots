package com.vwdigitalhub.cleaningrobots.application.dto;

public record RobotDto(String position) {
    public RobotDto {
        position = String.join(" ", position.split(""));
    }
}