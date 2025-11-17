import "./MoviePage.css";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchMovieById, fetchMoviesRelated, } from "../../services/movieService.jsx";
import { fetchActorByMovie } from "../../services/actorService.jsx";
import Footer from "../../organisms/Footer/Footer";
import MovieDisplay from "../../organisms/MovieDisplay/MovieDisplay";
import ComunityReview from "../../molecules/ComunityReview/ComunityReview";
import LineDivider from "../../atoms/LineDivider/LineDivider";
import RelatedMovieCard from "../../molecules/RelatedMovieCard/RelatedMovieCard.jsx";
import MyReview from "../../molecules/MyReview/MyReview.jsx";
import ActorCard from "../../molecules/ActorCard/ActorCard.jsx";

export default function MoviePage() {
    const { movieId } = useParams();

    const [movie, setMovie] = useState(null);
    const [actors, setActors] = useState([]);
    const [moviesRelated, setMoviesRelated] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadAllData = async () => {
            try {
                setIsLoading(true);
                setError(null);

                const [movieData, actorsData] = await Promise.all([
                    fetchMovieById(movieId),
                    fetchActorByMovie(movieId),
                ]);

                setMovie(movieData);
                setActors(actorsData);

                if (movieData && movieData.genre) {
                    const relatedData = await fetchMoviesRelated(movieData.genre, movieData.movie_title);

                    const shuffledData = [...relatedData];
                    for (let i = shuffledData.length - 1; i > 0; i--) {
                        const j = Math.floor(Math.random() * (i + 1));
                        [shuffledData[i], shuffledData[j]] = [shuffledData[j], shuffledData[i]];
                    }

                    const limitedData = shuffledData.slice(0, 3);
                    setMoviesRelated(limitedData);
                }
            } catch (err) {
                setError(err.message);
            } finally {
                setIsLoading(false);
            }
        };

        loadAllData();
    }, [movieId]);

    if (isLoading) {
        return <p className="error-states">Carregando filme...</p>;
    }

    if (error) {
        return <p className="error-states">Ocorreu um erro: {error}</p>;
    }

    if (!movie) {
        return <p className="error-states">Filme não encontrado.</p>;
    }

    return (
        <>
            <main className="movie-page-conteiner">
                <MovieDisplay movie={movie} />
                <section className="movie-page-container-grid">
                    <article className="movie-reviews">
                        <h3>Críticas da Comunidade</h3>
                        <ComunityReview
                            user_name={"Samuel"}
                            comment_text={
                                "Lorem Ipsum is simply dummy when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries."
                            }
                            comment_date={"2 dias"}
                            rating={"3.9"}
                        />
                        <LineDivider variant={"transparent"} />
                        <ComunityReview
                            user_name={"Samuel"}
                            comment_text={
                                "Typesetting industry. When an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries."
                            }
                            comment_date={"244 dias"}
                        />
                        <LineDivider variant={"transparent"} />
                        <MyReview />
                    </article>
                    <aside className="movie-page-related-line">
                        <h3>Filmes Relacionados</h3>
                        {moviesRelated.map((relatedMovie, index) => (
                            <RelatedMovieCard key={index} movie={relatedMovie} />
                        ))}
                    </aside>
                </section>
                <section className="movie-page-actor-conteiner">
                    <h4>Elenco Principal</h4>
                    <div>
                        {actors.map((actor, index) => (
                            <ActorCard key={index} actor={actor} />
                        ))}
                    </div>
                </section>
            </main>
            <Footer />
        </>
    );
}