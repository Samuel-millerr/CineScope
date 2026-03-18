package com.project.cinescope.request;

public enum RequestType {
    ADD("Adição"),
    EDIT("Edição");

    private final String type;

    RequestType(String type) {
        this.type = type;
    }
}
