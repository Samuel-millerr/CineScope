package com.project.cinescope.request.enums;

public enum RequestType {
    ADD("Adição"),
    EDIT("Edição");

    private final String type;

    RequestType(String type) {
        this.type = type;
    }
}
