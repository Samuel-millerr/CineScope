import "./HomeMoviesLine.css";
import HomeMovieCard from "../../molecules/HomeMovieCard/HomeMovieCard.jsx";
import HomeMovieHorizontalCard from "../../molecules/HomeMovieHorizontalCard/HomeMovieHorizontalCard.jsx";

export default function HomeMoviesLine({ movies, variant = "four" }) {
    // Card utilizado para fazer um layout simples de filme, pode conter entre 3 ou 4 filmes na linha
    if (variant === "four") {
        return (
            <article className="home-page-line-movies">
                {movies.map((movie, index) => (
                    <HomeMovieCard
                        key={index}
                        movie={movie}
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
                            movie={movie}
                        />
                    );
                })}
            </article>
        );
    }
    return null;
}
