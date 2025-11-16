import "./HomeMovieCard.css";
import Star from "../../atoms/Star/Star.jsx";

export default function HomeMovieCard({ movie }) {
    // Card dos filmes na tela home, recebe um filme específico e coloca as informações na tela
    return (
        <article className="home-movie-card">
            <figure className="home-movie-card-image-container">
                <img src={movie.movie_poster} alt={`Poster do filme ${movie.movie_name}`} />
            </figure>

            <div className="home-movie-card-info">
                <h3>{movie.movie_name}</h3>

                <div className="home-movie-info-bottom">
                    <figure className="home-movie-rating">
                        <Star variant="full" />
                        <p>{movie.movie_review_number}</p>
                    </figure>
                </div>
            </div>
        </article>
    );
}
