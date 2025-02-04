package com.vwdigitalhub.cleaningrobots.domain.valueobject;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidMovementLetterException;
import java.util.HashMap;
import java.util.Map;

public enum MovementLetter {
    LEFT("L"),
    RIGHT("R"),
    MOVE("M");

    private final String value;
    private static final Map<String, MovementLetter> valueMap = new HashMap<>();

    static {
        for (MovementLetter letter : values()) {
            valueMap.put(letter.value, letter);
        }
    }

    MovementLetter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MovementLetter fromValue(String value) {
        MovementLetter letter = valueMap.get(value);
        if (letter != null){
            return letter;
        }
        throw new InvalidMovementLetterException(
                String.format("Invalid movement letter: %s",(value == null)?"":value)
        );
    }
}
