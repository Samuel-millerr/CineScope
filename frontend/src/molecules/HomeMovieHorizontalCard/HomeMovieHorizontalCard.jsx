import "./HomeMovieHorizontalCard.css";

export default function HomeMovieHorizontalCard({variant, movie}) {
    // Card horizontal de filmes da tela home
    const classes = `home-movie-horizontal-card ${variant}`
    return (
        <article className={classes}>
            <figure className="movie-horizontal-card-container-img">
                <img src={movie.movie_poster} alt={`Foto do poster do filme ${movie.movie_name}`} />
            </figure>
            <div>
                <h5 className="movie-name">{movie.movie_name}</h5>
                <p>{movie.movie_year} | {movie.movie_genre}</p>    
            </div>
        </article>
    )
}