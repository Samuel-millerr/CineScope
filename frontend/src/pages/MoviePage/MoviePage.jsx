import "./MoviePage.css";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import { fetchMovieById, fetchMoviesRelated } from "../../services/movieService.jsx";
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
    const [reviews, setReviews] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    const API_BASE_URL = "http://localhost:8000/api"; // ajuste conforme seu backend

    // Função para buscar reviews usando fetch
    const fetchReviewsByMovie = async (movieId) => {
        try {
            const res = await fetch(`${API_BASE_URL}/reviews/${movieId}`);
            if (!res.ok) throw new Error("Erro ao buscar reviews");
            const data = await res.json();
            return data;
        } catch (err) {
            console.error("Erro ao buscar reviews:", err);
            return [];
        }
    };

    useEffect(() => {
        const loadAllData = async () => {
            try {
                setIsLoading(true);
                setError(null);

                const [movieData, actorsData, reviewsData] = await Promise.all([
                    fetchMovieById(movieId),
                    fetchActorByMovie(movieId),
                    fetchReviewsByMovie(movieId),
                ]);

                setMovie(movieData);
                setActors(actorsData);
                setReviews(reviewsData);

                if (movieData && movieData.genre) {
                    const relatedData = await fetchMoviesRelated(movieData.genre, movieData.movie_title);

                    const shuffledData = [...relatedData];
                    for (let i = shuffledData.length - 1; i > 0; i--) {
                        const j = Math.floor(Math.random() * (i + 1));
                        [shuffledData[i], shuffledData[j]] = [shuffledData[j], shuffledData[i]];
                    }

                    setMoviesRelated(shuffledData.slice(0, 3));
                }
            } catch (err) {
                setError(err.message);
            } finally {
                setIsLoading(false);
            }
        };

        loadAllData();
    }, [movieId]);

    if (isLoading) return <p className="error-states">Carregando filme...</p>;
    if (error) return <p className="error-states">Ocorreu um erro: {error}</p>;
    if (!movie) return <p className="error-states">Filme não encontrado.</p>;

    return (
        <>
            <main className="movie-page-conteiner">
                <MovieDisplay movie={movie} />
                <section className="movie-page-container-grid">
                    <article className="movie-reviews">
                        <h3>Críticas da Comunidade</h3>
                        {reviews.length > 0 ? (
                            reviews.map((review, index) => (
                                <div key={index}>
                                    <ComunityReview
                                        user_name={review.user_name}
                                        comment_text={review.review_text}
                                        comment_date={review.review_date}
                                        rating={review.review_rating}
                                    />
                                    {index < reviews.length - 1 && <LineDivider variant={"transparent"} />}
                                </div>
                            ))
                        ) : (
                            <p>Nenhuma crítica ainda.</p>
                        )}
                        <LineDivider variant={"purple"} />
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
