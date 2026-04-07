package com.project.cinescope.request.enums;

public enum RequestStatus {
    APROVADO("Aprovado"),
    PENDENTE("Pendente"),
    REPROVADO("Reprovado");

    private final String name;

    private RequestStatus(String name) {
        this.name = name;
    }
}
