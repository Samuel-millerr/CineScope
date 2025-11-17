import "./HomeMovieCard.css";
import Star from "../../atoms/Star/Star.jsx";
import { Link } from "react-router-dom";

export default function HomeMovieCard({ movie }) {
    // Card dos filmes na tela home, recebe um filme específico e coloca as informações na tela
    return (
        <Link to={`/movie/${movie.id_movie}`} className="search-card-link">
            <article className="home-movie-card">
                <figure className="home-movie-card-image-container">
                    <img src={movie.movie_poster} alt={`Poster do filme ${movie.movie_title}`} />
                </figure>

                <div className="home-movie-card-info">
                    <h3>{movie.movie_title}</h3>
                    <figure className="home-movie-rating">
                        <Star variant="full" />
                        <p>{movie.avg_rating}</p>
                    </figure>
                </div>
            </article>
        </Link >
    );
}
