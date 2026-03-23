package com.project.cinescope.request.enums;

public enum RequestStatus {
    APPROVED("Aprovado"),
    PENDING("Pendente"),
    DISAPPROVED("Reprovado");

    private final String status;

    RequestStatus(String status) {
        this.status = status;
    }
}
