package com.project.cinescope.user.enums;

public enum UserRole {
    COMUM("Comum"),
    ADMINISTRADOR("Administrador");

    private final String name;

    private UserRole(String name) {
        this.name = name;
    }
}
