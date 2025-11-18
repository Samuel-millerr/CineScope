const API_URL = "http://localhost:8000/api/movies"

export const fetchMoviesSimpleInfo = async () => {
    const response = await fetch(API_URL+"?filter=movies_simple_info");

    if (!response.ok) {
        throw new Error("Falha ao buscar os filmes no servidor.");
    }

    const data = await response.json();
    console.log(data)
    return data;
}

export const fetchMovieById = async (id_movie) => {
    const response = await fetch(API_URL+`/${id_movie}?filter=movie_simple_info`);

    if (!response.ok) {
        throw new Error("Falha ao buscar o filme no servidor.");
    }

    const data = await response.json();
    console.log(data)
    return data;
}

export const fetchMoviesRelated = async (genre, movie_title) => {
    const response = await fetch(API_URL+`?filter=movies_related?genre=${genre}?movie_title=${movie_title}`)

    if (!response.ok) {
        throw new Error("Falha ao buscar o filme no servidor.");
    }

    const data = await response.json();
    console.log(data)
    return data;
}

export const fetchMoviesAdmList = async () => {
    const response = await fetch(API_URL+`?filter=movies_adm_list`)

    if (!response.ok) {
        throw new Error("Falha ao buscar o filme no servidor.");
    }

    const data = await response.json();
    return data;
}

