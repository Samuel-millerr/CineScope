import "./PerfilMyReviews.css";
import { useState } from "react";
import { useEffect } from "react";
import Title from "../../atoms/Title/Title";
import MyReviewsCard from "../../molecules/MyReviewsCard/MyReviewsCard";
import { useAuth } from "../../AuthContext";

export default function PerfilMyReviews() {
    // Componente para visualizar as reviews feitas pelo usuário
    const [reviews, setReviews] = useState(null);
    const [ratingLine, setRatingLine] = useState(null);
    const { auth, logout } = useAuth();
    const userId = auth.user;

    useEffect(() => {
        const fetchMovies = async () => {
            try {
                const response = await fetch(`http://localhost:8000/api/reviews_user/${userId}`);
                if (!response.ok) throw new Error("Erro ao buscar avaliações");

                let data = await response.json();

                setReviews(data);
                console.log(data)
            } catch (error) {
                console.error("Erro ao carregar as reviews:", error);
            }
        };

        fetchMovies();
    }, []);

    if (!reviews) {
        return <p className="error-states">Carregando suas avaliações...</p>
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