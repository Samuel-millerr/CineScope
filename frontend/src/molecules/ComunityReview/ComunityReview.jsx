import "./ComunityReview.css";
import UserIcon from "../../assets/icons/avatar-icon.svg";
import RatingFilterLine from "../RatingFilterLine/RatingFilterLine.jsx";
import { useState } from "react";

export default function ComunityReview({user_icon = UserIcon, user_name, comment_text, comment_date, rating="0.0"}) {
    const [lineRating, setLineRating] = useState();
    
    return (
        <article className="comunity-review">
            <div className="comunity-review-main">
                <figure>
                    <img src={user_icon} alt="Ícone de avatar"/>
                </figure>
                <div className="comunity-review-info">
                    <h5 className="purple-text">{user_name}</h5>
                    <p>{comment_text}</p>
                    <RatingFilterLine rating={rating} isSelect={setLineRating}/>
                </div>
            </div>
            <p className="purple-text">{comment_date}</p>
        </article>
    )
}