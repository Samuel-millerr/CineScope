import "./PerfilCollection.css";
import { useState } from "react";
import { useEffect } from "react";
import PerfilMovieCard from "../../molecules/PerfilMovieCard/PerfilMovieCard.jsx";
import Title from "../../atoms/Title/Title.jsx";

export default function PerfilCollection() {
    // Componente para mostrar a coleção de filmes/filmes favoritos do usuário
    const [movies, setMovies] = useState(null);

    useEffect(() => {
        const fetchMovies = () => {
            const fetchedMovies = [
                { movie_name: "Duna", movie_poster: "https://acdn-us.mitiendanube.com/stores/004/687/740/products/pos-02290-bad6c8a814c0d7a2da17181238447778-480-0.jpg" },
                { movie_name: "Duna", movie_poster: "https://acdn-us.mitiendanube.com/stores/004/687/740/products/pos-02290-bad6c8a814c0d7a2da17181238447778-480-0.jpg" },
                { movie_name: "Duna", movie_poster: "https://acdn-us.mitiendanube.com/stores/004/687/740/products/pos-02290-bad6c8a814c0d7a2da17181238447778-480-0.jpg" },
                { movie_name: "Duna", movie_poster: "https://acdn-us.mitiendanube.com/stores/004/687/740/products/pos-02290-bad6c8a814c0d7a2da17181238447778-480-0.jpg" },
                { movie_name: "Duna", movie_poster: "https://acdn-us.mitiendanube.com/stores/004/687/740/products/pos-02290-bad6c8a814c0d7a2da17181238447778-480-0.jpg" },
            ];
            setMovies(fetchedMovies);
        };

        fetchMovies();
    }, []);

    if (!movies) {
        return <div>Carregando filmes...</div>
    }

    return (
        <section className="perfil-section-conteiner perfil-collection-container">
            <Title variant={"perfil"} title={"Minha Coleção"} />
            <div className="perfil-collection-movies-line">
                {
                    Object.entries(movies).map(([key, value]) =>
                        <PerfilMovieCard key={key} button_variant={"red"} text_button={"Remover"} movie={value} />
                    )
                }
            </div>
        </section>
    )
}