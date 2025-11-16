import SearchMovieCard from "../../molecules/SearchMovieCard/SearchMovieCard.jsx";

export default function SearchMoviesLine({movies}) {
    // Componente para organizar os filmes da página de busca
    return (
        <article className="home-page-line-movies">
            {movies.map((movie, index) => (
                <SearchMovieCard
                    key={index}
                    movie={movie}
                />
            ))}
        </article>
    )
}