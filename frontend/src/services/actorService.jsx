const API_URL = "http://localhost:8000/api/actors"

export const fetchAllActors = async () => {
    const response = await fetch(API_URL);

    if (!response.ok) {
        throw new Error("Falha ao buscar os atores no servidor.");
    }

    const data = await response.json()
    return data
}

export const fetchActorByMovie = async (id_movie) => {
    const response = await fetch(API_URL+`?filter=actor_by_movie?id_movie=${id_movie}`);

    if (!response.ok) {
        throw new Error("Falha ao buscar o ator no servidor.");
    }

    const data = await response.json()
    console.log(data)
    return data
}

