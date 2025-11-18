import "./PerfilAddEditMovie.css";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Title from "../../atoms/Title/Title";
import PerfilMovieCard from "../../molecules/PerfilMovieCard/PerfilMovieCard";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import Button from "../../atoms/Button/Button.jsx";

import { fetchMoviesSimpleInfo } from "../../services/movieService";

export default function PerfilAddEditMovie() {
    // Aba de visualização de todos os filmes do site para permitir o usuário adicionar ou editar um filme
    const [movies, setMovies] = useState([]);
    const [loading, setLoading] = useState(true);

    const [currentPage, setCurrentPage] = useState(1);
    const moviesPerPage = 10;

    useEffect(() => {
        async function loadMovies() {
            try {
                const data = await fetchMoviesSimpleInfo();
                setMovies(data);
            } catch (err) {
                console.error("Erro ao buscar filmes:", err);
            } finally {
                setLoading(false);
            }
        }

        loadMovies();
    }, []);

    if (loading) return <div className="error-states">Carregando filmes...</div>;

    const indexLast = currentPage * moviesPerPage;
    const indexFirst = indexLast - moviesPerPage;
    const currentMovies = movies.slice(indexFirst, indexLast);
    const totalPages = Math.ceil(movies.length / moviesPerPage);

    return (
        <section className="perfil-section-conteiner perfil-add-edit-movie-container">
            <Title variant={"perfil"} title={"Adicione ou edite filmes já cadastrados"} />

            <div className="perfil-add-edit-movie-movies-line">
                {currentMovies.map((movie) => (
                    <PerfilMovieCard
                        key={movie.id_movie}
                        button_variant={"grey"}
                        text_button={"Editar"}
                        movie={movie}
                    />
                ))}
            </div>

            <LineDivider variant={"purple"} />

            <div className="perfil-add-edit-movie-footer">
                <div className="pagination">
                    {Array.from({ length: totalPages }, (_, i) => (
                        <button
                            key={i}
                            onClick={() => setCurrentPage(i + 1)}
                            className={`pagination-btn ${
                                currentPage === i + 1 ? "active" : ""
                            }`}
                        >
                            {i + 1}
                        </button>
                    ))}
                </div>

                <Link to={"/add-movie"}>
                    <Button variant={"blue-gradient"} text_button={"Adicionar um novo filme"} />
                </Link>
            </div>
        </section>
    );
}
