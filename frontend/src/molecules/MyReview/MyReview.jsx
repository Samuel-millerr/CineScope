import "./MyReview.css";
import { useState } from "react";
import RatingFilterLine from "../RatingFilterLine/RatingFilterLine.jsx";
import TextArea from "../../atoms/TextArea/TextArea.jsx";
import Button from "../../atoms/Button/Button.jsx";

export default function MyReview() {
    const [ratingLine, setRatingLine] = useState();
    // Componente para permitir a inserção de um comentário do usuário cadastrado
    return (
        <article className="my-review">
            <h4> Deixe sua avaliação </h4>
            <RatingFilterLine rating={"0.0"} isSelected={setRatingLine}/>
            <TextArea variant={"black"} placeholder={"Escreva sua avaliação aqui..."} htmlFor={"comment"}/>
            <Button variant={"purple"} text_button={"Publicar"}/>
        </article>
    )
}