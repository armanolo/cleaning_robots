package com.vwdigitalhub.cleaningrobots.application.mapper;

import com.vwdigitalhub.cleaningrobots.domain.Robot;
import com.vwdigitalhub.cleaningrobots.application.dto.RobotDto;

import java.util.List;

public class RobotMapper {
    public RobotDto entityToDto(Robot robot){
        return new RobotDto(robot.getPosition());
    }

    public List<RobotDto> entitiesToDtos(List<Robot> robotList){
        return robotList.stream().map(this::entityToDto).toList();
    }
}
