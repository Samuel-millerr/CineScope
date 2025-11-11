import "./HomeMovieHorizontalCard.css";

export default function HomeMovieHorizontalCard({variant, movie_poster, movie_name, movie_year, movie_genre}) {
    const classes = `home-movie-horizontal-card ${variant}`
    return (
        <article className={classes}>
            <figure className="movie-horizontal-card-container-img">
                <img src={movie_poster} alt={`Foto do poster do filme ${movie_name}`} />
            </figure>
            <div>
                <h4 className="movie-name">{movie_name}</h4>
                <p>{movie_year} | {movie_genre}</p>    
            </div>
        </article>
    )
}