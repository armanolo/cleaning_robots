package com.vwdigitalhub.cleaningrobots.domain.service;

import com.vwdigitalhub.cleaningrobots.domain.Robot;
import com.vwdigitalhub.cleaningrobots.domain.Square;
import com.vwdigitalhub.cleaningrobots.domain.interfaces.ManagementRobotsService;
import java.util.List;

public class ManagementRobotsServiceImpl implements ManagementRobotsService {

    private Square square;
    private List<Robot> robotList;

    public void execute(Square square, List<Robot> robotList) {
        this.square = square;
        this.robotList = robotList;
        this.robotList.forEach(this.square::addObserver);
        this.robotList.forEach(Robot::movement);
    }

    public List<Robot> getRobotsPosition() {
        return robotList;
    }

    public Square getSquare() {
        return square;
    }
}
