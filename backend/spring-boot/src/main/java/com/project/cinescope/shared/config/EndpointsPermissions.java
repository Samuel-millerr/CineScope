package com.project.cinescope.shared.config;

import org.springframework.http.HttpMethod;

public enum EndpointsPermissions {
    AUTH_LOGIN(HttpMethod.POST, ApiEndpoints.Auth.LOGIN, Access.PUBLIC),
    AUTH_REGISTER(HttpMethod.POST, ApiEndpoints.Auth.REGISTER, Access.PUBLIC),

    ACTORS_CREATE(HttpMethod.POST, ApiEndpoints.Actors.BASE, Access.ADMIN),
    ACTORS_DELETE(HttpMethod.POST, ApiEndpoints.Actors.BY_ID, Access.ADMIN),

    DIRECTORS_CREATE(HttpMethod.POST, ApiEndpoints.Directors.BASE, Access.ADMIN),
    DIRECTORS_DELETE(HttpMethod.POST, ApiEndpoints.Directors.BY_ID, Access.ADMIN),

    MOVIES_CREATE(HttpMethod.POST, ApiEndpoints.Movies.BASE, Access.ADMIN),
    MOVIES_DELETE(HttpMethod.POST, ApiEndpoints.Movies.BY_ID, Access.ADMIN),

    USERS_LIST(HttpMethod.POST, ApiEndpoints.Users.BASE, Access.ADMIN);

    public final HttpMethod method;
    public final String path;
    public final Access access;

    EndpointsPermissions(HttpMethod method, String path, Access access) {
        this.method = method;
        this.path = path;
        this.access = access;
    }

    public enum Access {PUBLIC, ADMIN}
}
