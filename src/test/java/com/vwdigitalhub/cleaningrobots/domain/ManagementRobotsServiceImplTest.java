package com.vwdigitalhub.cleaningrobots.domain;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.CoordinatesOutOfSquareException;
import com.vwdigitalhub.cleaningrobots.domain.exceptions.RobotInTheSamePositionException;
import com.vwdigitalhub.cleaningrobots.domain.service.ManagementRobotsServiceImpl;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.OrientedPosition;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.RobotMovements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;


class ManagementRobotsServiceImplTest {

    private final ManagementRobotsServiceImpl managementRobotsServiceImpl = new ManagementRobotsServiceImpl();

    Square properSquare = new Square("5 5");
    List<Robot> properRobotList = List.of(
            new Robot(new OrientedPosition("1 2 N"),new RobotMovements("LMLMLMLMM"),properSquare),
            new Robot(new OrientedPosition("3 3 E"), new RobotMovements("MMRMMRMRRM"),properSquare)
    );

    @Test
    @DisplayName("show process the robot movements")
    void processRobotMovements() {
        // When
        managementRobotsServiceImpl.execute(properSquare, properRobotList);

        // Then
        assertThat(properRobotList.size()).isSameAs(2);
        assertEquals("13N", properRobotList.get(0).getPosition());
        assertEquals("51E", properRobotList.get(1).getPosition());
    }

    @Test
    @DisplayName("should throw an exception because of robot will try to move out of the square")
    void shouldThrowExceptionByRobotMovingOutOfTheSquare() {
        // Given
        Square square = new Square("1 1");
        List<Robot> robotList = List.of(
                new Robot(new OrientedPosition("1 2 N"), new RobotMovements("LMLMLMLMM"),square),
                new Robot(new OrientedPosition("3 3 E"), new RobotMovements("MMRMMRMRRM"),square)
        );

        // When
        assertThatThrownBy(
                () -> managementRobotsServiceImpl.execute(square, robotList)
        ).isInstanceOf(CoordinatesOutOfSquareException.class).hasMessage("Robot can't move out of the square");
    }

    @Test
    @DisplayName("Should throw an exception because there are robot in the same position")
    void shouldThrowExceptionByRobotInTheSamePosition() {
        Square square = new Square("5 5");
        List<Robot> robotList = List.of(
                new Robot(new OrientedPosition("1 1 N"), new RobotMovements("RMLM"), square),
                new Robot(new OrientedPosition("2 2 N"), new RobotMovements("MMM"), square)
        );

        assertThatThrownBy(
                () -> managementRobotsServiceImpl.execute(square, robotList)
        ).isInstanceOf(RobotInTheSamePositionException.class).hasMessage("there is another robot in the same position: 22");
    }

    @Test
    @DisplayName("should throw an exception because of conflict with another robot")
    void shouldThrowExceptionByMovementConflict() {
        Square square = new Square("5 5");
        List<Robot> robotList = List.of(
                new Robot(new OrientedPosition("1 1 N"), new RobotMovements("RMLM"),square),
                new Robot(new OrientedPosition("1 1 N"),new RobotMovements("MRM"),square)
        );

        assertThatThrownBy(
                () -> managementRobotsServiceImpl.execute(square, robotList)
        ).isInstanceOf(RobotInTheSamePositionException.class).hasMessage("there is another robot in the same position: 22");
    }
}
