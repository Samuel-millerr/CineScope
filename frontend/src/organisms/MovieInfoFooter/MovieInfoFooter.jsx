import "./MovieInfoFooter.css";
import { useState } from "react";
import RatingFilterLine from "../../molecules/RatingFilterLine/RatingFilterLine";
import Button from "../../atoms/Button/Button";

export default function MovieInfoFooter({ ratingNumber }) {
    // Footer utilizado na parte de display do filme
    const [ratingFilterLine, setRatingFilterLine] = useState()
    return (
        <article className="movie-info-footer">
            <div>
                <h5>Avaliação Média</h5>
                <div className="rating-container">
                    <RatingFilterLine rating={ratingNumber} isSelected={setRatingFilterLine} />
                    <p>{ratingNumber}</p>
                </div>
            </div>
            <div>
                <Button variant={"purple"} text_button={"Adicionar a minha coleção"} />
                <Button variant={"transparent"} text_button={"Marcar como assistido"} />
            </div>
        </article>
    )
}