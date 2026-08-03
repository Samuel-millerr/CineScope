package com.project.cinescope.core.utils.message;

public enum ErrorEnum {
    ;

    private final String message;

    ErrorEnum(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return String.format(this.message, args);
    }
}
