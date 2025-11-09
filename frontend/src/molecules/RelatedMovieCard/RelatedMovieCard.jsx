import "./RelatedMovieCard.css"
import "../../atoms/Star/Star.jsx"
import Star from "../../atoms/Star/Star.jsx"

export default function RelatedMovieCard({movie_poster, movie_name, movie_review_number, movie_year, movie_genre}) {
    return (
        <article className="related-movie-card">
            <figure className="related-movie-poster">
                <img src={movie_poster} alt={`Poster do filme ${movie_name}`}/>
            </figure>
            <div className="related-movie-info">
                <h4>{movie_name}</h4>
                <figure className="related-movie-review-container">
                    <Star variant={"full"}/>
                    <p>{movie_review_number}</p>
                </figure>

                <p>{movie_year} | {movie_genre}</p>
            </div>
        </article>
    )
}