package com.vwdigitalhub.cleaningrobots.infrastructure;

import com.vwdigitalhub.cleaningrobots.application.ManageRobotsUseCase;
import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidInputDataException;
import com.vwdigitalhub.cleaningrobots.application.dto.OrderedArguments;
import com.vwdigitalhub.cleaningrobots.application.dto.RobotsPosition;
import com.vwdigitalhub.cleaningrobots.domain.service.ManagementRobotsServiceImpl;

public class ManageRobotHandler {

    private final ManageRobotsUseCase manageRobotsUseCase;

    public ManageRobotHandler(){
        this.manageRobotsUseCase = new ManageRobotsUseCase(new ManagementRobotsServiceImpl());
    }

    public String manage(String input){
        OrderedArguments orderedArguments = new OrderedArguments(input);

        if (orderedArguments.getArguments().isEmpty()){
            throw new InvalidInputDataException("Input is empty");
        }

        RobotsPosition lastPositions = manageRobotsUseCase.manage(orderedArguments);

        return lastPositions.toString();
    }
}
