import "./RequestCard.css";
import Chip from "../../atoms/Chip/Chip";

export default function RequestCard({ request }) {
    // Card de requisões mostradas ao usuário, tem uma lógica para fazer a verificação de qual chip vai ser utilizado de acordo com seu status
    const chip_variant = (request.request_status === "Aprovado") ? "approved" : (request.request_status === "Pendente") ? "pending" : "failed";
    return (
        <article className="request-card">
            <figure className="request-image-container">
                <img src={request.movie_image} className="request-image" alt={`Poster do filme ${request.movie_name}`} />
            </figure>
            <div className="request-info-container">
                <h3>{request.movie_name}</h3>
                <p>{request.request_date}</p>
                <div className="request-sub-info">
                    <h4>Tipo de requisição:</h4>
                    <Chip variant={"purple"} chip_text={request.request_type} />
                </div>
                <div className="request-sub-info">
                    <h4>Status:</h4>
                    <Chip variant={chip_variant} chip_text={request.request_status} />
                </div>
            </div>
        </article>
    )
}