import "./ActorCard.css";

export default function ActorCard({actor_image, actor_name}) {
    return (
        <article className="card-actor-conteiner">
            <figure className="actor-image-container">
                <img src={actor_image} className="actor-image" alt={`Foto do ator ${actor_name}`}/>
            </figure>
            <h5 className="actor-name">{actor_name}</h5>
        </article>
    )
}