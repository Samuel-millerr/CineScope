import "./HomeMoviesLine.css";
import HomeMovieCard from "../../molecules/HomeMovieCard/HomeMovieCard.jsx";
import HomeMovieHorizontalCard from "../../molecules/HomeMovieHorizontalCard/HomeMovieHorizontalCard.jsx";

export default function HomeMoviesLine({ movies, variant = "four" }) {
    if (variant === "four") {
        return (
            <article className="home-page-line-movies">
                {movies.map((movie, index) => (
                    <HomeMovieCard
                        key={index}
                        movie_poster={movie.movie_poster}
                        movie_name={movie.movie_name}
                        movie_review_number={movie.movie_review_number}
                        favorite_icon_variant={movie.favorite_icon_variant}
                    />
                ))}
            </article>
        );
    }

    if (variant === "horizontal") {
        return (
            <article className="home-page-line-movies">
                {movies.map((movie, index) => {
                    const card_variant = index === 1 ? "large" : "small";

                    return (
                        <HomeMovieHorizontalCard
                            key={index}
                            variant={card_variant}
                            movie_poster={movie.movie_poster}
                            movie_name={movie.movie_name}
                            movie_year={movie.movie_year}
                            movie_genre={movie.movie_genre}
                        />
                    );
                })}
            </article>
        );
    }
    return null;
}
