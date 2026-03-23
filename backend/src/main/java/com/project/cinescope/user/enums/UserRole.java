package com.project.cinescope.user.enums;

public enum UserRole {
    COMMON("Comum"),
    ADM("Administrador");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
