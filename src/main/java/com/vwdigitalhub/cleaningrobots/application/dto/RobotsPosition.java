package com.vwdigitalhub.cleaningrobots.application.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record RobotsPosition(List<RobotDto> positions) {
    @Override
    public String toString() {
        return positions.stream().flatMap( it -> Stream.of( it.position()) )
                .collect( Collectors.joining("\n"));
    }
}
