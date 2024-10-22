package com.alex.tasktable.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Statuses {
    @JsonProperty("NEW")
    NEW("New"),
    @JsonProperty("IN_PROCESS")
    IN_PROCESS("In process"),
    @JsonProperty("CLOSED")
    CLOSED("Closed");

    private final String name;

    Statuses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
