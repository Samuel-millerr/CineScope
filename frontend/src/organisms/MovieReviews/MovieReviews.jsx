import "./MovieReviews.css";
import ComunityReview from "../../molecules/ComunityReview/ComunityReview.jsx"
import LineDivider from "../../atoms/LineDivider/LineDivider.jsx";

export default function MovieReviews() {
    return (
        <article>
            <h3>Críticas da Comunidade</h3>
            <ComunityReview
                user_name={"Samuel"}
                comment_text={"Vai tudo pra puta que pariu"}
                comment_date={"12/10/2025"}    
            />
            <LineDivider variant={"grey"}/>
            <ComunityReview
                user_name={"Samuel"}
                comment_text={"Vai tudo pra puta que pariu"}
                comment_date={"12/10/2025"}    
            />
        </article>
    )
}