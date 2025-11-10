import "./MyReviewsCard.css";
import RatingFilterLine from "../RatingFilterLine/RatingFilterLine.jsx"

export default function MyReviewsCard({movie_poster, title, comment, rating, setLineRating}) {
    return (
        <article className="my-reviews-card">
            <figure className="my-reviews-card-poster">
                <img src={movie_poster}/>
            </figure>
            <div className="my-reviews-card-info">
                <h5>{title}</h5>
                <RatingFilterLine rating={rating} isSelect={setLineRating}/>
                <p>{comment}</p>
            </div>
        </article>
    )
}