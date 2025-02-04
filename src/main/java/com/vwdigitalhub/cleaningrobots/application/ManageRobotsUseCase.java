package com.vwdigitalhub.cleaningrobots.application;

import com.vwdigitalhub.cleaningrobots.application.mapper.RobotMapper;
import com.vwdigitalhub.cleaningrobots.domain.Robot;
import com.vwdigitalhub.cleaningrobots.domain.Square;
import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidInputDataException;
import com.vwdigitalhub.cleaningrobots.domain.interfaces.ManagementRobotsService;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.OrientedPosition;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.RobotMovements;
import com.vwdigitalhub.cleaningrobots.application.dto.OrderedArguments;
import com.vwdigitalhub.cleaningrobots.application.dto.RobotsPosition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ManageRobotsUseCase {

    private final RobotMapper robotMapper = new RobotMapper();

    private final ManagementRobotsService managementRobotsService;

    public ManageRobotsUseCase(ManagementRobotsService managementRobotsService){
        this.managementRobotsService = managementRobotsService;
    }

    public RobotsPosition manage(OrderedArguments arguments) {
        List<String> orderedArguments = arguments.getArguments();
        Square square;
        List<Robot> robotList = new ArrayList<>();

        Iterator<String> iterator = orderedArguments.iterator();
        try{
            square = new Square(iterator.next());
            while (iterator.hasNext()){

                OrientedPosition orientedPosition = new OrientedPosition(iterator.next());
                RobotMovements robotMovements = new RobotMovements(iterator.next());

                robotList.add(
                        new Robot(orientedPosition, robotMovements, square)
                );
            }
        }catch (NoSuchElementException e) {
            throw new InvalidInputDataException("Invalid input data");
        }

        managementRobotsService.execute(square, robotList);
        return new RobotsPosition(robotMapper.entitiesToDtos(managementRobotsService.getRobotsPosition()));
    }
}
