import "./PerfilMovieCard.css";
import SmallButton from "../../atoms/SmallButton/SmallButton.jsx";
import { Link } from "react-router-dom";

export default function PerfilMovieCard({ type = "submit", button_variant, text_button, movie }) {
    return (
        <article className="perfil-movie-card">
            <figure>
                <img
                    src={movie.movie_poster}
                    alt={`Poster do filme ${movie.movie_title}`}
                    className="perfil-movie-image"
                />
            </figure>

            <div className="perfil-movie-card-footer">
                <h5>{movie.movie_title}</h5>

                {console.log(movie.id_movie)}
                <Link to={`/edit-movie/${movie.id_movie}`}>
                    <SmallButton
                        type={type}
                        variant={button_variant}
                        text_button={text_button}
                    />
                </Link>
            </div>
        </article>
    );
}
