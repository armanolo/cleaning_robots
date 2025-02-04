package com.vwdigitalhub.cleaningrobots.domain.valueobject;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidCardinalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardinalTest {

    @Test
    @DisplayName("Valid CardinalPoint by string parameter")
    void validCardinalPointByStringParameters(){
        Cardinal cardinalPoint = Cardinal.fromValue("N");
        assertThat(cardinalPoint).isEqualTo(Cardinal.NORTH);
        assertThat(cardinalPoint.getValue()).isEqualTo("N");
    }

    @Test
    @DisplayName("should throw an exception for invalid cardinal")
    void shouldThrowAnExceptionForInvalidCoordinates(){
        assertThatThrownBy(
                () -> Cardinal.fromValue("T")
        ).isInstanceOf(InvalidCardinalException.class)
                .hasMessage("Invalid cardinal value: T");
    }

}
