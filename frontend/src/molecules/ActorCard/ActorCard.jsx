import "./ActorCard.css";

export default function CardActor({actor_image, actor_name}) {
    return (
        <article className="card-actor-conteiner">
            <figure className="actor-image-container">
                <img src={actor_image} className="actor-image" alt={`Foto do ator ${actor_name}`}/>
            </figure>
            <h4 className="actor-name">{actor_name}</h4>
        </article>
    )
}