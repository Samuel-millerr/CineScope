package com.project.cinescope.application.request.enums;

public enum RequestType {
    ADIÇÃO("Adição"),
    EDIÇÃO("Edição");

    private final String name;

    private RequestType(String name) {
        this.name = name;
    }
}
