import "./PerfilMyReviews.css";
import { useState } from "react";
import { useEffect } from "react";
import Title from "../../atoms/Title/Title";
import MyReviewsCard from "../../molecules/MyReviewsCard/MyReviewsCard";

export default function PerfilMyReviews() {
    // Componente para visualizar as reviews feitas pelo usuário
    const [reviews, setReviews] = useState(null);
    const [ratingLine, setRatingLine] = useState(null);

    useEffect(() => {

        const fetchMovies = () => {
            const fetchedReviews = [
                { movie_title: "Duna", movie_poster: "https://acdn-us.mitiendanube.com/stores/004/687/740/products/pos-02290-bad6c8a814c0d7a2da17181238447778-480-0.jpg", movie_comment: "'Eu odiaei a porra desese filme com todas as minhas forças, parace uma bostinha derretida dentro do microondas'" },
                { movie_title: "Duna", movie_poster: "https://acdn-us.mitiendanube.com/stores/004/687/740/products/pos-02290-bad6c8a814c0d7a2da17181238447778-480-0.jpg", movie_comment: "'Eu odiaei a porra desese filme com todas as minhas forças, parace uma bostinha derretida dentro do microondas'" },
            ];
            setReviews(fetchedReviews);
        };

        fetchMovies();
    }, []);

    if (!reviews) {
        return <div>Carregando filmes...</div>
    }
    console.log(reviews)
    return (
        <section className="perfil-section-conteiner perfil-reviews-container">
            <Title title={"Minhas Avaliações"} variant={"perfil"} />
            <div className="perfil-reviews-column">
                {
                    reviews.map((review, index) =>
                        <MyReviewsCard key={index} review={review} rating={5} setLineRating={setRatingLine} />
                    )
                }
            </div>
        </section>
    )
}