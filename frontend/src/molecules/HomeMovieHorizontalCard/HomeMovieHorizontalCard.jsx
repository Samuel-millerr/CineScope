import { Link } from "react-router-dom";
import "./HomeMovieHorizontalCard.css";

export default function HomeMovieHorizontalCard({variant, movie}) {
    // Card horizontal de filmes da tela home
    const classes = `home-movie-horizontal-card ${variant}`
    return (
        <Link to={`/movie/${movie.id_movie}`}>
            <article className={classes}>
                <figure className="movie-horizontal-card-container-img">
                    <img src={movie.movie_poster} alt={`Foto do poster do filme ${movie.movie_title}`} />
                </figure>
                <div>
                    <h5 className="movie-name">{movie.movie_title}</h5>
                    <p>{movie.publication_year} | {movie.genres.split(", ")[0]}</p>    
                </div>
            </article>
        </Link>
    )
}