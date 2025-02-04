package com.vwdigitalhub.cleaningrobots.domain;

import com.vwdigitalhub.cleaningrobots.domain.valueobject.Cardinal;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.OrientedPosition;
import com.vwdigitalhub.cleaningrobots.domain.valueobject.RobotMovements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {

    private final Square square = new Square("5 5");

    @ParameterizedTest
    @MethodSource("robotInputData")
    @DisplayName("With input {input} then the robot return x as {x}, y as {y} and cardinal as {cardinal}")
    void setInput(String input, Integer expectedX, Integer expectedY, Cardinal cardinal) {
        Robot robot = new Robot(new OrientedPosition(input), new RobotMovements("MM"), square);
        assertEquals(expectedX, robot.getRobotPosition().getX());
        assertEquals(expectedY, robot.getRobotPosition().getY());
        assertEquals(cardinal, robot.getCardinal());
    }

    private static Stream<Arguments> robotInputData() {
        return Stream.of(
                Arguments.of("1 2 N", 1, 2, Cardinal.NORTH),
                Arguments.of("3 3 E", 3, 3, Cardinal.EAST)
        );
    }

    @ParameterizedTest
    @MethodSource("robotInputDataAndListOfMovements")
    @DisplayName("With input {input} and movement {movement} then the robot return x as {x}, y as {y} and cardinal as {cardinal}")
    void listMovements(String input, String listOfMovements, Integer expectedX, Integer expectedY, Cardinal cardinal) {
        Robot robot = new Robot(new OrientedPosition(input), new RobotMovements(listOfMovements),square);
        robot.movement();
        assertEquals(expectedX, robot.getRobotPosition().getX());
        assertEquals(expectedY, robot.getRobotPosition().getY());
        assertEquals(cardinal, robot.getCardinal());
    }

    private static Stream<Arguments> robotInputDataAndListOfMovements() {
        return Stream.of(
                Arguments.of("1 2 N", "LMLMLMLMM", 1,3, Cardinal.NORTH),
                Arguments.of("3 3 E", "MMRMMRMRRM", 5, 1, Cardinal.EAST),
                Arguments.of("4 1 S", "M", 4, 0, Cardinal.SOUTH)
        );
    }
}
