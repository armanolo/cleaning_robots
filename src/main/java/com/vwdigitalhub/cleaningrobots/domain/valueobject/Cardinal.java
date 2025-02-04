package com.vwdigitalhub.cleaningrobots.domain.valueobject;

import com.vwdigitalhub.cleaningrobots.domain.exceptions.InvalidCardinalException;
import java.util.HashMap;
import java.util.Map;

public enum Cardinal {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private final String value;
    private static final Map<String, Cardinal> valueMap = new HashMap<>();

    static {
        for (Cardinal cardinal : values()) {
            valueMap.put(cardinal.value, cardinal);
        }
    }


    Cardinal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Cardinal fromValue(String value) {
        Cardinal cardinal = valueMap.get(value);
        if (cardinal != null) {
            return cardinal;
        }
        throw new InvalidCardinalException(
                String.format("Invalid cardinal value: %s", (value == null ? "" : value))
        );
    }
}
