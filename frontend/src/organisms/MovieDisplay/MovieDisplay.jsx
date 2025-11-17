import "./MovieDisplay.css";
import MovieInfoFooter from "../MovieInfoFooter/MovieInfoFooter";
import Chip from "../../atoms/Chip/Chip.jsx";
import Breadcrumb from "../../molecules/Breadcrumb/Breadcrumb.jsx";

export default function MovieDisplay({ movie }) {
    return (
        <section className="movie-display">
            <Breadcrumb items={{
                "Home": "/",
                "Pesquisa": "/search",
                "Filme": "/"
            }} />

            <figure className="movie-display-poster">
                <img src={movie.movie_poster} alt={`Poster do filme ${movie.movie_title}`} />
            </figure>

            <article className="movie-display-info">
                <div>
                    <h1>{movie.movie_title}</h1>

                    <div className="chips-container">
                        <Chip variant={"dark-blue"} chip_text={movie.publication_year}></Chip>
                        <Chip variant={"dark-blue"} chip_text={movie.genre}></Chip>
                        <Chip variant={"dark-blue"} chip_text={movie.duration_time}></Chip>
                    </div>
                </div>
                <div>
                    <h2>Sinopse</h2>
                    <p className="synopsis-text">{movie.movie_synopsis}</p>
                </div>
                <div className="credits">
                    <p><strong>Diretor:</strong> {movie.directors}</p>
                </div>

                <MovieInfoFooter ratingNumber={movie.avg_rating}/>
            </article>
        </section>
    )
}