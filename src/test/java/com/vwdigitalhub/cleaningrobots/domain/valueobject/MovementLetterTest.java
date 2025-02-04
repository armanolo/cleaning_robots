package com.vwdigitalhub.cleaningrobots.domain.valueobject;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidMovementLetterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovementLetterTest {


    @Test
    @DisplayName("Valid MovementLetter by string parameter")
    void validCardinalPointByStringParameters(){
        MovementLetter movementLetter = MovementLetter.fromValue("M");
        assertThat(movementLetter.getValue()).isEqualTo("M");
    }

    @Test
    @DisplayName("Should throw an exception because of bad movement letter data")
    void shouldThrowExceptionByInvalidRobotMovements() {
        assertThatThrownBy(
                () -> MovementLetter.fromValue("1")
        ).isInstanceOf(InvalidMovementLetterException.class).hasMessage("Invalid movement letter: 1");
    }
}
