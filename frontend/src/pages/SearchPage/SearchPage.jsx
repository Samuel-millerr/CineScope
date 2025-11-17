import "./SearchPage.css";
import { useEffect, useState, useMemo } from "react";
import { fetchMoviesSimpleInfo } from "../../services/movieService.jsx";
import { useSearchParams } from "react-router-dom";
import SearchBanner from "../../organisms/SearchBanner/SearchBanner.jsx";
import SearchFilters from "../../organisms/SearchFilters/SearchFilters.jsx";
import Breadcrumb from "../../molecules/Breadcrumb/Breadcrumb.jsx";
import Title from "../../atoms/Title/Title.jsx";
import Footer from "../../organisms/Footer/Footer.jsx";
import SearchMovieCard from "../../molecules/SearchMovieCard/SearchMovieCard.jsx";

export default function SearchPage() {
    const [movies, setMovies] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    const [searchParams] = useSearchParams();
    const searchTerm = searchParams.get('q') || "";
    const [selectedGenres, setSelectedGenres] = useState([]);
    const [yearRange, setYearRange] = useState({ min: 1990, max: 2025 });
    const [selectedRatings, setSelectedRatings] = useState([]);
    const [director, setDirector] = useState("");
    const [actor, setActor] = useState("");

    useEffect(() => {
        const loadData = async () => {
            try {
                const data = await fetchMoviesSimpleInfo();
                setMovies(data);
            } catch (err) {
                setError(err.message)
            } finally {
                setIsLoading(false)
            };
        }

        loadData();
    }, []);

    const filteredMovies = useMemo(() => {
        const searchTerm = searchParams.get('search') || "";

        if (
            searchTerm.trim() === "" &&
            selectedGenres.length === 0 &&
            director.trim() === "" &&
            actor.trim() === "" &&
            selectedRatings.length === 0 &&
            yearRange.min === 1990 &&
            yearRange.max === 2025
        ) {
            return movies;
        }

        // 3. Lógica de filtragem principal
        return movies.filter(movie => {
            if (searchTerm.trim() !== "") {
                const titleMatches = movie.movie_title?.toLowerCase()?.includes(searchTerm.toLowerCase().trim());
                if (!titleMatches) {
                    return false;
                }
            }

            if (selectedGenres.length > 0) {
                const hasAnyGenre = selectedGenres.some(genre =>
                    movie.genres?.toLowerCase()?.includes(genre.toLowerCase())
                );
                if (!hasAnyGenre) {
                    return false;
                }
            }

            if (movie.publication_year < yearRange.min || movie.publication_year > yearRange.max) {
                return false;
            }

            if (selectedRatings.length > 0) {
                const movieStarRating = Math.round(movie.avg_rating);
                if (!selectedRatings.includes(movieStarRating)) {
                    return false;
                }
            }

            if (director.trim() !== "") {
                if (!movie.directors?.toLowerCase()?.includes(director.toLowerCase().trim())) {
                    return false;
                }
            }
            
            if (actor.trim() !== "") {
                const hasActor = movie.actors?.toLowerCase()?.includes(actor.toLowerCase().trim());
                if (!hasActor) {
                    return false;
                }
            }

            return true;
        });

    }, [movies, selectedGenres, yearRange, selectedRatings, director, actor, searchParams]);

    return (
        <>
            <main className="search-page-container">
                <SearchBanner />
                <section className="search-page-query-conteiner">
                    <div>
                        <Breadcrumb items={{
                            "Home": "/",
                            "Pesquisa": "/search"
                        }} />
                        <SearchFilters
                            selectedGenres={selectedGenres}
                            yearRange={yearRange}
                            selectedRatings={selectedRatings}
                            director={director}
                            actor={actor}

                            setSelectedGenres={setSelectedGenres}
                            setYearRange={setYearRange}
                            setSelectedRatings={setSelectedRatings}
                            setDirector={setDirector}
                            setActor={setActor}
                        />
                    </div>
                    <div>
                        <Title variant={"search"} title={"Resultados"} />
                        {isLoading && <p className="error-states">Carregando filmes...</p>}
                        {error && <p className="error-states">Ocorreu um erro: {error}</p>}
                        {!isLoading && !error && filteredMovies.length === 0 && (
                            <p className="error-states">Nenhum filme encontrado com os filtros aplicados.</p>
                        )}

                        <div className="search-results-grid">
                            {!isLoading && !error && filteredMovies.length > 0 &&
                                filteredMovies.map((movie) => (
                                    <SearchMovieCard key={movie.id_movie} movie={movie} />
                                ))
                            }
                        </div>
                    </div>
                </section>
            </main>
            <Footer />
        </>
    )
}