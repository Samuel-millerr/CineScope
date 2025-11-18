import "./SearchMovieCard.css";
import Star from "../../atoms/Star/Star.jsx";
import { Link } from "react-router-dom";

export default function SearchMovieCard({ movie }) {
    // Componente de cards para a a tela de pesquisa
    return (
        <Link to={`/movie/${movie.id_movie}`} className="search-card-link">
            <article className="search-movie-card">
                <figure className="search-movie-card-image-container">
                    <img src={movie.movie_poster} alt={`Poster do filme ${movie.movie_name}`} />
                </figure>

                <div className="search-movie-card-info">
                    <h3>{movie.movie_title}</h3>

                    <div className="search-movie-info-bottom">
                        <p className="search-movie-subtitle">
                            {movie.publication_year} | {movie.genres.split(", ")[0]}
                        </p>
                        <figure className="search-movie-rating">
                            <Star variant="full" />
                            <p>{movie.avg_rating}</p>
                        </figure>
                    </div>
                </div>
            </article>
        </Link>
    );
}