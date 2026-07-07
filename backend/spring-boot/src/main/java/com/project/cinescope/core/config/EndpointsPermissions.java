package com.project.cinescope.core.config;

import org.springframework.http.HttpMethod;

public enum EndpointsPermissions {
    AUTH_LOGIN(HttpMethod.POST, ApiEndpoints.Auth.LOGIN, Access.PUBLIC),
    AUTH_REGISTER(HttpMethod.POST, ApiEndpoints.Auth.REGISTER, Access.PUBLIC),
    AUTH_HEALTH(HttpMethod.GET, ApiEndpoints.Auth.HEALTH, Access.PUBLIC),

    ACTORS_CREATE(HttpMethod.POST, ApiEndpoints.Actors.BASE, Access.ADMIN),
    ACTORS_PATCH(HttpMethod.PATCH, ApiEndpoints.Actors.BY_ID, Access.ADMIN),
    ACTORS_DELETE(HttpMethod.POST, ApiEndpoints.Actors.BY_ID, Access.ADMIN),

    DIRECTORS_CREATE(HttpMethod.POST, ApiEndpoints.Directors.BASE, Access.ADMIN),
    DIRECTORS_PATCH(HttpMethod.PATCH, ApiEndpoints.Directors.BY_ID, Access.ADMIN),
    DIRECTORS_DELETE(HttpMethod.POST, ApiEndpoints.Directors.BY_ID, Access.ADMIN),

    GENRES_CREATE(HttpMethod.POST, ApiEndpoints.Genres.BASE, Access.ADMIN),
    GENRES_DELETE(HttpMethod.DELETE, ApiEndpoints.Genres.BY_ID, Access.ADMIN),

    MOVIES_CREATE(HttpMethod.POST, ApiEndpoints.Movies.BASE, Access.ADMIN),
    MOVIES_LIST(HttpMethod.GET, ApiEndpoints.Movies.BASE, Access.PUBLIC), // Permitira a visualização dos filmes na tela inicial sem a necessidade de login
    MOVIES_UPDATE(HttpMethod.PATCH, ApiEndpoints.Movies.BY_ID, Access.ADMIN),
    MOVIES_DELETE(HttpMethod.DELETE, ApiEndpoints.Movies.BY_ID, Access.ADMIN),

    REQUESTS_LIST(HttpMethod.GET, ApiEndpoints.Requests.BASE, Access.ADMIN),
    REQUESTS_BY_ID(HttpMethod.GET, ApiEndpoints.Requests.BY_ID, Access.ADMIN),
    REQUESTS_BY_CURRENT_USER(HttpMethod.GET, ApiEndpoints.Users.REQUESTS_BY_CURRENT_USER, Access.AUTHENTICATED),
    REQUESTS_BY_USER_ID(HttpMethod.GET, ApiEndpoints.Users.REQUESTS_BY_USER_ID, Access.ADMIN),
    REQUESTS_DELETE(HttpMethod.DELETE, ApiEndpoints.Requests.BY_ID, Access.ADMIN),
//    REQUESTS_DELETE_BY_CURRENT_USER(HttpMethod.DELETE, ApiEndpoints.Users.REVIEWS_BY_CURRENT_USER + "/{id}", Access.AUTHENTICATED),

    REVIEWS_LIST(HttpMethod.GET, ApiEndpoints.Reviews.BASE, Access.ADMIN),
    REVIEWS_BY_ID(HttpMethod.GET, ApiEndpoints.Reviews.BY_ID, Access.ADMIN),
    REVIEWS_BY_CURRENT_USERS(HttpMethod.GET, ApiEndpoints.Users.REVIEWS_BY_CURRENT_USER, Access.AUTHENTICATED),
    REVIEWS_BY_USER_ID(HttpMethod.GET, ApiEndpoints.Users.REVIEWS_BY_USER_ID, Access.ADMIN),
    REVIEWS_DELETE(HttpMethod.DELETE, ApiEndpoints.Reviews.BY_ID, Access.ADMIN),

    USERS_LIST(HttpMethod.GET, ApiEndpoints.Users.BASE, Access.ADMIN),
    USERS_LIST_BY_ID(HttpMethod.GET, ApiEndpoints.Users.BY_ID, Access.ADMIN),
    USERS_LIST_BY_USERNAME(HttpMethod.GET, ApiEndpoints.Users.BY_USERNAME, Access.ADMIN);

    public final HttpMethod method;
    public final String path;
    public final Access access;

    EndpointsPermissions(HttpMethod method, String path, Access access) {
        this.method = method;
        this.path = path;
        this.access = access;
    }

    public enum Access {PUBLIC, AUTHENTICATED, ADMIN}
}
