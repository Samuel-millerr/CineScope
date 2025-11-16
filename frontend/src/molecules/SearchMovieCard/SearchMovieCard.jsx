import "./SearchMovieCard.css";
import Star from "../../atoms/Star/Star.jsx";

export default function SearchMovieCard({ movie }) {
    // Componente de cards para a a tela de pesquisa
    return (
        <article className="search-movie-card">
            <figure className="search-movie-card-image-container">
                <img src={movie.movie_poster} alt={`Poster do filme ${movie.movie_name}`} />
            </figure>

            <div className="search-movie-card-info">
                <h3>{movie.movie_name}</h3>

                <div className="search-movie-info-bottom">
                    <p className="search-movie-subtitle">
                        {movie.movie_year} | {movie.movie_genre}
                    </p>
                    <figure className="search-movie-rating">
                        <Star variant="full" />
                        <p>{movie.movie_review_number}</p>
                    </figure>
                </div>
            </div>
        </article>
    );
}