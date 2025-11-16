import "./MyReviewsCard.css";
import RatingFilterLine from "../RatingFilterLine/RatingFilterLine.jsx"

export default function MyReviewsCard({review, rating, setLineRating}) {
    // Componente para armazenar as reviews do usuário logado 
    return (
        <article className="my-reviews-card">
            <figure className="my-reviews-card-poster">
                <img src={review.movie_poster} alt={`Poster do filme ${review.movie_title}`}/>
            </figure>
            <div className="my-reviews-card-info">
                <h5>{review.movie_title}</h5>
                <RatingFilterLine rating={rating} isSelect={setLineRating}/>
                <p>"{review.movie_comment}"</p>
            </div>
        </article>
    )
}