import SearchMovieCard from "../../molecules/SearchMovieCard/SearchMovieCard.jsx";

export default function SearchMoviesLine({movies}) {
    return (
        <article className="home-page-line-movies">
            {movies.map((movie, index) => (
                <SearchMovieCard
                    key={index}
                    movie_poster={movie.movie_poster}
                    movie_name={movie.movie_name}
                    movie_review_number={movie.movie_review_number}
                />
            ))}
        </article>
    )
}