package com.project.cinescope.core.utils.message;

public enum ErrorEnum {
    NOT_FOUND(
            "%s not found",
            "%s not found with %s: %s"
    ),

    DUPLICATE(
            "%s already exists",
            "%s already exists with %s: %s"
    );

    private final String simpleMessageTemplate;
    private final String detailedMessageTemplate;

    ErrorEnum(String simpleMessageTemplate, String detailedMessageTemplate) {
        this.simpleMessageTemplate = simpleMessageTemplate;
        this.detailedMessageTemplate = detailedMessageTemplate;
    }

    public String formatSimpleTemplate(String entity) {
        return String.format(simpleMessageTemplate, entity);
    }

    public String formatDetailedTemplate(String entity, String field, Object value) {
        return String.format(detailedMessageTemplate, entity, field, value);
    }
}
