import "./PerfilMovieCard.css";
import SmallButton from "../../atoms/SmallButton/SmallButton.jsx"

export default function PerfilMovieCard({type="submit", button_variant, text_button, movie}) {
    return (
        <article className="perfil-movie-card">
            <figure>
                <img src={movie.movie_poster}  alt={`Poster do filme ${movie.movie_name}`} className="perfil-movie-image"/>
            </figure>
            <div className="perfil-movie-card-footer">
                <h5>{movie.movie_name}</h5>
                <SmallButton type={type} variant={button_variant} text_button={text_button}/>
            </div>
        </article>
    )
}