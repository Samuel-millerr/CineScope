import "./PerfilAddEditMovie.css";
import { useState } from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import Title from "../../atoms/Title/Title";
import PerfilMovieCard from "../../molecules/PerfilMovieCard/PerfilMovieCard";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import Button from "../../atoms/Button/Button.jsx";

export default function PerfilAddEditMovie() {
    const [movies, setMovies] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const moviesPerPage = 10;

    useEffect(() => {
        const fetchMovies = () => {
            const fetchedMovies = Array.from({ length: 35 }).map(() => ({
                movie_name: "Duna",
                movie_poster: "https://acdn-us.mitiendanube.com/stores/004/687/740/products/pos-02290-bad6c8a814c0d7a2da17181238447778-480-0.jpg"
            }));
            setMovies(fetchedMovies);
        };

        fetchMovies();
    }, []);

    if (!movies) return <div>Carregando filmes...</div>

    const indexLast = currentPage * moviesPerPage;
    const indexFirst = indexLast - moviesPerPage;
    const currentMovies = movies.slice(indexFirst, indexLast);
    const totalPages = Math.ceil(movies.length / moviesPerPage);

    return (
        <section className="perfil-section-conteiner perfil-add-edit-movie-container">
            <Title variant={"perfil"} title={"Adicione ou edite filmes já cadastrados"} />
            <div className="perfil-add-edit-movie-movies-line">
                {currentMovies.map((movie, index) => (
                    <PerfilMovieCard
                        key={index}
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
                            className={`pagination-btn ${currentPage === i + 1 ? "active" : ""}`}
                        >
                            {i + 1}
                        </button>
                    ))}
                </div>
                <Link to={"/add-movie"}><Button variant={"blue-gradient"} text_button={"Adicionar um novo filme"} /></Link>
            </div>
        </section>
    )
}