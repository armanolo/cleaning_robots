package com.vwdigitalhub.cleaningrobots.domain.valueobject;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidCoordinatesException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RobotsPositionTest {

    private RobotPosition robotPosition;

    @Test
    @DisplayName("Valid RobotPosition by string parameters")
    void validRobotPositionByStringParameters(){
        robotPosition = new RobotPosition("1","2");
        assertThat(robotPosition.getX()).isEqualTo(1);
        assertThat(robotPosition.getY()).isEqualTo(2);
        assertThat(robotPosition.toString()).hasToString("12");
    }

    @Test
    @DisplayName("Valid RobotPosition by integer parameters")
    void validRobotPositionByIntegerParameters(){
        robotPosition = new RobotPosition(1,2);
        assertThat(robotPosition.getX()).isEqualTo(1);
        assertThat(robotPosition.getY()).isEqualTo(2);
    }

    @Test
    @DisplayName("should throw an exception for invalid coordinates")
    void shouldThrowAnExceptionForInvalidCoordinates(){
        assertThatThrownBy(
                () -> new RobotPosition("","")
        ).isInstanceOf(InvalidCoordinatesException.class)
                .hasMessage("Robot position is invalid: coordinates not valid");
    }


    @Test
    @DisplayName("should throw an exception for negatives coordinates")
    void shouldThrowAnExceptionForNegativeCoordinates(){
        assertThatThrownBy(
                () -> new RobotPosition(-1,0)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Coordinates must be non-negative");
    }

}
