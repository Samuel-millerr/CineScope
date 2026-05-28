package com.project.cinescope.shared.config;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ApiEndpoints {
    public static class Auth {
        public static final String BASE = "/api/auth";
        public static final String LOGIN = "/api/auth/login";
        public static final String REGISTER = "/api/auth/register";
    }

    public static class Actors {
        public static final String BASE = "/api/actors";
        public static final String BY_ID = "/api/actors/{id}";
    }

    public static class Directors {
        public static final String BASE = "/directors";
        public static final String BY_ID = "/api/directors/{id}";
    }

    public static class Movies {
        public static final String BASE = "/movies";
        public static final String BY_ID = "/api/movies/{id}";
    }

    public static class Users {
        public static final String BASE = "/users";
        public static final String BY_ID = "/api/users/{id}";
    }
}
