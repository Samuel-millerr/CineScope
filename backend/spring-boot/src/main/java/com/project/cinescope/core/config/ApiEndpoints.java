package com.project.cinescope.core.config;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ApiEndpoints {
    public static class Auth {
        public static final String BASE = "/api/auth";
        public static final String LOGIN = BASE + "/login";
        public static final String REGISTER = BASE + "/register";
        public static final String HEALTH = BASE + "/health";
    }

    public static class Actors {
        public static final String BASE = "/api/actors";
        public static final String BY_ID = BASE +"/{id}";
    }

    public static class Directors {
        public static final String BASE = "/api/directors";
        public static final String BY_ID = BASE + "/{id}";
    }

    public static class Genres {
        public static final String BASE = "/api/genres";
        public static final String BY_ID = BASE + "/{id}";
    }

    public static class Movies {
        public static final String BASE = "/api/movies";
        public static final String BY_ID = BASE + "/{id}";
    }

    public static class Requests {
        public static final String BASE = "/api/requests";
        public static final String BY_ID = BASE + "/{id}";
    }

    public static class Reviews {
        public static final String BASE = "/api/reviews";
        public static final String BY_ID = BASE + "/{id}";
    }

    public static class Users {
        public static final String BASE = "/api/users";
        public static final String BY_ID = BASE + "/id/{id}";
        public static final String BY_USERNAME = BASE + "/username/{username}";
        public static final String REQUESTS_BY_CURRENT_USER = BASE + "/me/requests";
        public static final String REQUESTS_BY_USER_ID = BASE + "/{id}/requests";
        public static final String REVIEWS_BY_CURRENT_USER = BASE + "/me/reviews";
        public static final String REVIEWS_BY_USER_ID = BASE + "/{id}/reviews";
    }
}
