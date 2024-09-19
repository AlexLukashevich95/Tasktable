package com.alex.tasktable.enums;

public enum Statuses {
    NEW("New"),
    IN_PROCESS("In process"),
    CLOSED("Closed");

    private final String name;

    Statuses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
