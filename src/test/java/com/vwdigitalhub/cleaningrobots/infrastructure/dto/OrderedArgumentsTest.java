package com.vwdigitalhub.cleaningrobots.infrastructure.dto;

import com.vwdigitalhub.cleaningrobots.application.dto.OrderedArguments;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class OrderedArgumentsTest {

    @Test
    void createWithEscapedNewLines() {
        // Given
        String input = "55\n11N\nM";

        // When
        OrderedArguments orderedArguments = new OrderedArguments(input);

        // Then
        assertThat(orderedArguments.getArguments()).hasSize(3);
        assertThat(orderedArguments.getArguments()).containsExactly("55", "11N", "M");

    }

    @Test
    void createWithNewLines() {
        // Given
        String input = """
                55
                11N
                M
                """;

        // When
        OrderedArguments orderedArguments = new OrderedArguments(input);

        // Then
        assertThat(orderedArguments.getArguments()).hasSize(3);
        assertThat(orderedArguments.getArguments()).containsExactly("55", "11N", "M");
    }
}