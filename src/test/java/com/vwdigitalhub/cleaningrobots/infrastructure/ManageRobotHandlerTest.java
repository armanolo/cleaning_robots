package com.vwdigitalhub.cleaningrobots.infrastructure;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidInputDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManageRobotHandlerTest {

    private ManageRobotHandler manageRobotHandler;

    @BeforeEach
    void setupt(){
        manageRobotHandler = new ManageRobotHandler();
    }

    @Test
    @DisplayName("should process the input")
    void manageProperInput() {
        // Given
        String input = "5 5\n1 2 N\nM";

        // When
        String result = manageRobotHandler.manage(input);

        // Then
        assertThat(result).hasToString("1 3 N");
    }

    @Test
    @DisplayName("should throw an exception because of empty input")
    void manageEmptyInput() {
        // Given
        String empty = "";

        // When
        assertThatThrownBy(
                () -> manageRobotHandler.manage(empty)
        ).isInstanceOf(InvalidInputDataException.class).hasMessage("Input is empty");
    }
}