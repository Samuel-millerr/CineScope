import "./MovieDisplay.css";
import MovieInfoFooter from "../MovieInfoFooter/MovieInfoFooter";
import Chip from "../../atoms/Chip/Chip.jsx";
import Breadcrumb from "../../molecules/Breadcrumb/Breadcrumb.jsx";

export default function MovieDisplay({ movie }) {
    // Display de filme individual
    return (
        <section className="movie-display">
            <Breadcrumb items={{
                "Home": "/",
                "Pesquisa": "/search",
                "Filme": "/"
            }} />

            <figure className="movie-display-poster">
                <img src={movie.movie_poster} alt={`Poster do filme ${movie.movie_name}`} />
            </figure>

            <article className="movie-display-info">
                <div>
                    <h1>{movie.movie_name}</h1>

                    <div className="chips-container">
                        <Chip variant={"dark-blue"} chip_text={movie.movie_year}></Chip>
                        <Chip variant={"dark-blue"} chip_text={movie.movie_genre}></Chip>
                        <Chip variant={"dark-blue"} chip_text={movie.movie_duration}></Chip>
                    </div>
                </div>
                <div>
                    <h2>Sinopse</h2>
                    <p className="synopsis-text">{movie.movie_summary}</p>
                </div>
                <div className="credits">
                    <p><strong>Diretor:</strong> {movie.movie_director}</p>
                    <p><strong>Produtora:</strong> {movie.movie_producer}</p>
                </div>

                <MovieInfoFooter />
            </article>
        </section>
    )
}