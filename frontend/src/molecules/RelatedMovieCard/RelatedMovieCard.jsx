import "./RelatedMovieCard.css"
import { Link } from "react-router-dom"
import Star from "../../atoms/Star/Star.jsx"

export default function RelatedMovieCard({movie}) {
    // Card de filmes relacionados a algum dentro da tela de filme específico
    return (
        <Link to={`/movie/${movie.id_movie}`} className="related-movie-link">
            <article className="related-movie-card">
                <figure className="related-movie-poster">
                    <img src={movie.movie_poster} alt={`Poster do filme ${movie.movie_title}`}/>
                </figure>
                <div className="related-movie-info">
                    <h4>{movie.movie_title}</h4>
                    <figure className="related-movie-review-container">
                        <Star variant={"full"}/>
                        <p>{movie.avg_rating}</p>
                    </figure>

                    <p>{movie.publication_year} | {movie.genre}</p>
                </div>
            </article>
        </Link>
    )
}