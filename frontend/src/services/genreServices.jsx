const API_URL = "http://localhost:8000/api/genres"

export const fetchAllGenres = async () => {
    const response = await fetch(API_URL);

    if (!response.ok) {
        throw new Error("Falha ao buscar os filmes no servidor.");
    }

    const data = await response.json()
    console.log(data)
    return data
}
