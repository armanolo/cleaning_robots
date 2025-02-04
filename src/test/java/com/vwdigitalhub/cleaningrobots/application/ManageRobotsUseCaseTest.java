package com.vwdigitalhub.cleaningrobots.application;


import com.vwdigitalhub.cleaningrobots.domain.Robot;
import com.vwdigitalhub.cleaningrobots.domain.Square;
import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidInputDataException;
import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidRobotInputException;
import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidSquareInputException;
import com.vwdigitalhub.cleaningrobots.domain.service.ManagementRobotsServiceImpl;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.OrientedPosition;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.RobotMovements;
import com.vwdigitalhub.cleaningrobots.application.dto.OrderedArguments;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ManageRobotsUseCaseTest {

    @Mock
    private ManagementRobotsServiceImpl managementRobotsServiceImpl;

    @InjectMocks
    private ManageRobotsUseCase manageRobotsUseCase;

    private final Square square = new Square("5 5");
    private final List<Robot> robotList = List.of(
            new Robot(new OrientedPosition("1 2 N"), new RobotMovements("LMLMLMLMM"), square),
            new Robot(new OrientedPosition("3 3 E"), new RobotMovements("MMRMMRMRRM"), square)
    );

    @Test
    @DisplayName("should process the input data")
    void manageProperInput() {
        // Given
        String input = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM";
        OrderedArguments orderedArguments = new OrderedArguments(input);

        when(managementRobotsServiceImpl.getRobotsPosition()).thenReturn(robotList);

        // When
        manageRobotsUseCase.manage(orderedArguments);

        // Then
        verify(managementRobotsServiceImpl, times(1)).execute(
                square, robotList
        );
    }

    @Test
    @DisplayName("Should throw an exception because of invalid input data")
    void shouldThrowExceptionByInvalidInputData() {
        String input = "5 5\n1 1 N";
        OrderedArguments orderedArguments = new OrderedArguments(input);

        // When
        assertThatThrownBy(
                () -> manageRobotsUseCase.manage(orderedArguments)
        ).isInstanceOf(InvalidInputDataException.class).hasMessage("Invalid input data");
    }

    @Test
    @DisplayName("should throw an exception because of bad square input format")
    void shouldThrowExceptionByInvalidSquare() {
        // Given
        String input = "5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM";
        OrderedArguments orderedArguments = new OrderedArguments(input);

        // When
        assertThatThrownBy(
                () -> manageRobotsUseCase.manage(orderedArguments)
        ).isInstanceOf(InvalidSquareInputException.class).hasMessage("Invalid input data for upper-right square: 5");

    }

    @Test
    @DisplayName("Should throw an exception because of bad robot input format")
    void shouldThrowExceptionByInvalidRobot() {
        String input = "5 5\n1 N\nLMLMLMLMM";
        OrderedArguments orderedArguments = new OrderedArguments(input);

        assertThatThrownBy(
                () -> manageRobotsUseCase.manage(orderedArguments)
        ).isInstanceOf(InvalidRobotInputException.class).hasMessage("Input have to be split in: x y cardinal");
    }

}
