import "./MovieInfoFooter.css";
import { useState } from "react";
import RatingFilterLine from "../../molecules/RatingFilterLine/RatingFilterLine";
import Button from "../../atoms/Button/Button";

export default function MovieInfoFooter({ ratingNumber }) {
    // Footer utilizado na parte de display do filme
    const [ratingFilterLine, setRatingFilterLine] = useState()
    return (
        <article className="movie-info-footer">
            <h5>Avaliação Média</h5>
            <div className="rating-container">
                <RatingFilterLine rating={ratingNumber} isSelected={setRatingFilterLine} />
            </div>
        </article>
    )
}