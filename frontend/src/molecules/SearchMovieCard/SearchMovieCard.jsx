import "./SearchMovieCard.css";
import Star from "../../atoms/Star/Star.jsx";

export default function SearchMovieCard({
    movie_poster,
    movie_name,
    movie_year,
    movie_genre,
    movie_review_number,
}) {
    return (
        <article className="search-movie-card">
            <figure className="search-movie-card-image-container">
                <img src={movie_poster} alt={`Poster do filme ${movie_name}`} />
            </figure>

            <div className="search-movie-card-info">
                <h3>{movie_name}</h3>

                <div className="search-movie-info-bottom">
                    <p className="search-movie-subtitle">
                        {movie_year} | {movie_genre}
                    </p>
                    <figure className="search-movie-rating">
                        <Star variant="full" />
                        <p>{movie_review_number}</p>
                    </figure>
                </div>
            </div>
        </article>
    );
}