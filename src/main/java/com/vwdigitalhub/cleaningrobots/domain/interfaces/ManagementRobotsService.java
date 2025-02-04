package com.vwdigitalhub.cleaningrobots.domain.interfaces;

import com.vwdigitalhub.cleaningrobots.domain.Robot;
import com.vwdigitalhub.cleaningrobots.domain.Square;

import java.util.List;

public interface ManagementRobotsService {
    public void execute(Square square, List<Robot> robotList);
    public List<Robot> getRobotsPosition();
}
