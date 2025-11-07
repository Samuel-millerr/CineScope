import "./RequestCard.css";
import Chip from "../../atoms/Chip/Chip";

export default function RequestCard({movie_image, movie_name, request_date, request_type, resquest_status}) {
    const chip_variant = (resquest_status === "Aprovado") ? "approved" : (resquest_status === "Pendente") ? "pending" : "failed";
    return (
        <article className="request-card">
            <figure className="request-image-container">
                <img src={movie_image} className="request-image" alt={`Poster do filme ${movie_name}`}/>
            </figure>
            <div className="request-info-container">
                <h3>{movie_name}</h3>
                <p>{request_date}</p>
                <div className="request-sub-info">
                    <h4>Tipo de requisição:</h4>
                    <Chip variant={"purple"} chip_text={request_type}/>
                </div>
                <div className="request-sub-info">
                    <h4>Status:</h4>
                    <Chip variant={chip_variant} chip_text={resquest_status}/>
                </div>
            </div>
        </article>
    )
}