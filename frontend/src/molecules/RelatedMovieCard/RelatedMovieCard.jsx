import "./RelatedMovieCard.css"
import "../../atoms/Star/Star.jsx"
import Star from "../../atoms/Star/Star.jsx"

export default function RelatedMovieCard({movie}) {
    // Card de filmes relacionados a algum dentro da tela de filme específico
    return (
        <article className="related-movie-card">
            <figure className="related-movie-poster">
                <img src={movie.movie_poster} alt={`Poster do filme ${movie.movie_name}`}/>
            </figure>
            <div className="related-movie-info">
                <h4>{movie.movie_name}</h4>
                <figure className="related-movie-review-container">
                    <Star variant={"full"}/>
                    <p>{movie.movie_review_number}</p>
                </figure>

                <p>{movie.movie_year} | {movie.movie_genre}</p>
            </div>
        </article>
    )
}