package com.vwdigitalhub.cleaningrobots;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MainIntegrationTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testMainIntegrationWithValidArguments() {
        // Given
        String[] args = {"5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM"};
        String expectedResult = "1 3 N\n5 1 E";

        // Act: Run the application
        Main.main(args);

        // Assert: Verify expected output
        String consoleOutput = outputStreamCaptor.toString().trim();
        assertThat(consoleOutput).contains(expectedResult);
    }


    @Test
    void testMainIntegrationWithInvalidArguments() {
        // Arrange: Provide invalid arguments
        String[] args = {"--onlyOneArg"};

        // Act: Run the application
        Main.main(args);

        // Assert: Verify expected behavior (should not crash or process input)
        String consoleOutput = outputStreamCaptor.toString().trim();
        assertFalse(consoleOutput.contains("Cleaning completed"), "Should not process input when arguments are invalid.");
    }

}