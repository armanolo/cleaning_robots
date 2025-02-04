package com.vwdigitalhub.cleaningrobots.application.dto;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class OrderedArguments {
    private final LinkedList<String> arguments;

    public OrderedArguments(String input) {
        if (input == null || input.trim().isEmpty()) {
            arguments = new LinkedList<>();
        } else {
            arguments = new LinkedList<>(Arrays.asList(input.split("\n")));
        }
    }

    public List<String> getArguments(){
        return this.arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderedArguments that = (OrderedArguments) o;
        return Objects.equals(getArguments(), that.getArguments());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getArguments());
    }
}
