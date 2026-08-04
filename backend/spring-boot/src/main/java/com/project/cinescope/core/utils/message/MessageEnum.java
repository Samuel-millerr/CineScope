package com.project.cinescope.core.utils.message;

public enum MessageEnum {
    ;

    private final String message;

    MessageEnum(String message){
        this.message = message;
    }

    public String format(Object... args) {
        return String.format(this.message, args);
    }
}
